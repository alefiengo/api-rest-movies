package bo.alefiengo.apirestmovies;

import bo.alefiengo.apirestmovies.domain.entity.Movie;
import bo.alefiengo.apirestmovies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ApiRestMoviesApplication {

    @Autowired
    private MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApiRestMoviesApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Stream.of(
                    new Movie("TRR001", "Annabelle"),
                    new Movie("TRR002", "Host"),
                    new Movie("CMD001", "Super cool"),
                    new Movie("CMD002", "Flipped"),
                    new Movie("ROM001", "OneDay"),
                    new Movie("INF001", "Coco")
            ).map(movieRepository::save).forEach(System.out::println);
        };
    }

}
