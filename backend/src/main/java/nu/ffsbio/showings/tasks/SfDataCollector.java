package nu.ffsbio.showings.tasks;


import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nu.ffsbio.showings.config.MovieConfig;
import nu.ffsbio.showings.model.internal.Movie;
import nu.ffsbio.showings.model.sf.SfMovie;
import nu.ffsbio.showings.repository.MovieRepository;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SfDataCollector {
    private static final Logger LOG = LoggerFactory.getLogger(SfDataCollector.class);
    private MovieConfig movieConfig;
    private String sfMovieUrl;
    private static final Header USER_AGENT_HEADER = new BasicHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

    private final ImageDownloader imageDownloader;
    private final MovieRepository movieRepository;

    @Autowired
    public SfDataCollector(final MovieConfig movieConfig, final MovieRepository movieRepository, final ImageDownloader imageDownloader) {
        this.movieRepository = movieRepository;
        this.imageDownloader = imageDownloader;
        this.movieConfig = movieConfig;
        sfMovieUrl = movieConfig.getSfUrl();
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 12)
    public void populateCache() {
        long startTime = System.currentTimeMillis();
        try {

            //Cleanup all old entries
            movieRepository.deleteAll();

            String repose = getRepose(sfMovieUrl);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            List<SfMovie> movies = objectMapper.readValue(repose, new TypeReference<List<SfMovie>>() {
            });
            movies.parallelStream()
                    .forEach(this::saveData);

            Set<String> movieNames = movies.stream()
                    .map(SfMovie::getSlug)
                    .collect(Collectors.toSet());

            imageDownloader.deleteOldImages(movieNames);

        } catch (IOException e) {
            LOG.error("Failed to parse json: ", e);
        }
        double initTimeSeconds = (System.currentTimeMillis() - startTime) / 1000.0;
        LOG.info("Initialization finished in {} seconds", initTimeSeconds);
    }

    private String getRepose(String url) {
        String body = "";
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader(USER_AGENT_HEADER);

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            CloseableHttpResponse response = httpclient.execute(httpget);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity e = response.getEntity();
                String entity = EntityUtils.toString(e);
                body += entity;
            } else {
                body += statusLine;
            }
        } catch (IOException e) {
            LOG.error("Error with retrieving data from: " + sfMovieUrl, e);
        } finally {
            httpget.releaseConnection();
        }
        return body;
    }

    private void saveData(SfMovie movie) {
        imageDownloader.download(movie.getPosterUrl(), movie.getSlug());
        movieRepository.save(new Movie(movie));
    }
}
