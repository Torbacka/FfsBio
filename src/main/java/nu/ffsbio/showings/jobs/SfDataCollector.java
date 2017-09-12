package nu.ffsbio.showings.jobs;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nu.ffsbio.showings.model.internal.Movie;
import nu.ffsbio.showings.model.sf.Item;
import nu.ffsbio.showings.model.sf.MovieItems;
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
    private static final String SF_MOVIE_URL = "https://www.sf.se/api/v2/movie/sv/1/1024/";
    private static final Header USER_AGENT_HEADER = new BasicHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

    private final ImageDownloader imageDownloader;
    private final MovieRepository movieRepository;

    @Autowired
    public SfDataCollector(final MovieRepository movieRepository, final ImageDownloader imageDownloader) {
        this.movieRepository = movieRepository;
        this.imageDownloader = imageDownloader;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 12)
    public void populateCache() {
        try {
            //Cleanup all old entries
            movieRepository.deleteAll();

            String repose = getRepose(SF_MOVIE_URL);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            MovieItems movieItems = objectMapper.readValue(repose, MovieItems.class);

            movieItems.getItems()
                            .parallelStream()
                            .forEach(this::saveData);

            List<String> movieNames = movieItems.getItems()
                            .stream()
                            .map(Item::getSlug)
                            .collect(Collectors.toList());

            imageDownloader.deleteOldImages(movieNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRepose(String url) {

        String body = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader(USER_AGENT_HEADER);
        try {
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
            LOG.error("Error with retrieving data from: " + SF_MOVIE_URL, e);
        } finally {
            httpget.releaseConnection();
        }
        return body;
    }

    private void saveData(Item item) {
        imageDownloader.download(item.getImages(), item.getSlug());
        movieRepository.save(new Movie(item));
    }
}
