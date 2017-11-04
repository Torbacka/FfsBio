package nu.ffsbio.showings.controller;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Resources;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import util.MovieRepositoryLoader;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MovieControllerIT {
    @LocalServerPort
    private int serverPort;

    @Before
    public void setUp() throws IOException {
        RestAssured.port = serverPort;

        MovieRepositoryLoader movieRepositoryLoader = new MovieRepositoryLoader();
        String movieJson = Resources.getResource("movieData/movie.json").getFile();
        movieRepositoryLoader.load(new File(movieJson));
    }
    @Test
    public void test() {

    }
    /*
    @Test
    public void getAllMovies_shouldSucceed() {
        when()
            .get("/v1/movies")
        .then()
            .body("size()", is(2))
            .statusCode(HttpStatus.OK.value());
    }
    */

}
