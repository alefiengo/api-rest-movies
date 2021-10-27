package bo.alefiengo.apirestmovies.service;

import bo.alefiengo.apirestmovies.domain.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MovieService {

    Optional<Movie> findById(Integer id);

    Movie save(Movie movie);

    Movie update(Integer id, Movie movie);

    Iterable<Movie> findAll();

    void deleteById(Integer id);

}
