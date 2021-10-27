package bo.alefiengo.apirestmovies.repository;

import bo.alefiengo.apirestmovies.domain.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
