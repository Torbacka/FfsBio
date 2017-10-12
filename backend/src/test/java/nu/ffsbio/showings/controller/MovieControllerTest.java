package nu.ffsbio.showings.controller;

import nu.ffsbio.showings.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class MovieControllerTest {
    @Mock
    private MovieRepository movieRepository;

    private MovieController movieController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        movieController = new MovieController(movieRepository);
    }

    @Test
    public void getAllMovies_shouldSucceed() {
        movieController.getAllMovies();
        verify(movieRepository).findAll();
    }

    @Test
    public void getReleasedMovies_shouldSucceed() {
        movieController.getReleasedMovies();
        verify(movieRepository).findAll();
    }

    @Test
    public void getUpcomingMovies_shouldSucceed() {
        movieController.getUpcomingMovies();
        verify(movieRepository).findAll();
    }
}
