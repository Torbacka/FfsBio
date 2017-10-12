package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nu.ffsbio.showings.model.internal.Movie;
import nu.ffsbio.showings.model.sf.SfMovie;
import nu.ffsbio.showings.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("nu.ffsbio.showings.repository")
public class MovieRepositoryLoader {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Load a jsonfile into the MovieRepository
     *
     * @param file json data to load into in-memory database
     */
    public void load(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<SfMovie> movies = objectMapper.readValue(file, new TypeReference<List<SfMovie>>() {
        });
        movies.parallelStream()
                .forEach(sfMovie -> movieRepository.save(new Movie(sfMovie)));
    }
}
