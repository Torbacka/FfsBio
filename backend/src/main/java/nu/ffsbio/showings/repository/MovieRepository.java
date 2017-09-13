package nu.ffsbio.showings.repository;


import nu.ffsbio.showings.model.internal.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String>{

}
