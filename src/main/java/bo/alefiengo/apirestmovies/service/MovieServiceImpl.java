package bo.alefiengo.apirestmovies.service;

import bo.alefiengo.apirestmovies.domain.entity.Movie;
import bo.alefiengo.apirestmovies.exception.BadRequestException;
import bo.alefiengo.apirestmovies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findById(Integer id) {
        Optional<Movie> oMovie = movieRepository.findById(id);

        if (oMovie.isEmpty()) {
            throw new BadRequestException("No movie found");
        }
        return movieRepository.findById(id);
    }

    @Override
    @Transactional
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    @Transactional
    public Movie update(Integer id, Movie movie) {
        Optional<Movie> oMovie = movieRepository.findById(id);

        if (oMovie.isEmpty()) {
            throw new BadRequestException("No movie found");
        }

        Movie movieUpdate = oMovie.get();
        movieUpdate.setCode(movie.getCode());
        movieUpdate.setTitle(movie.getTitle());
        movieUpdate.setDescription(movie.getDescription());

        return movieRepository.save(movieUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Movie> findAll() {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();

        if (movies.isEmpty()) {
            throw new BadRequestException("No movies found");
        }

        return movieRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<Movie> oMovie = movieRepository.findById(id);

        if (oMovie.isEmpty()) {
            throw new BadRequestException("No movie found");
        }

        movieRepository.deleteById(id);
    }
}
