package nu.ffsbio.showings.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import nu.ffsbio.showings.model.internal.Movie;
import nu.ffsbio.showings.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {
    private MovieRepository movieRepository;

    @Autowired
    MovieController(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @RequestMapping(path = "/released", method = RequestMethod.GET)
    public Iterable<Movie> getReleasedMovies() {
        LocalDateTime now = LocalDateTime.now();

        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                        .filter(movie -> movie.getReleaseDate().isBefore(now))
                        .collect(Collectors.toList());
    }

    @RequestMapping(path = "/upcoming", method = RequestMethod.GET)
    public Iterable<Movie> getUpcomingMovies() {
        LocalDateTime now = LocalDateTime.now();

        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                        .filter(movie -> movie.getReleaseDate().isAfter(now))
                        .collect(Collectors.toList());
    }
}
