package bo.alefiengo.apirestmovies.controller;

import bo.alefiengo.apirestmovies.domain.entity.Movie;
import bo.alefiengo.apirestmovies.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Movie> movies = (List<Movie>) movieService.findAll();

        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Movie> oMovie = movieService.findById(id);

        return ResponseEntity.of(oMovie);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Movie movie, BindingResult result) {
        Map<String, Object> validations = new HashMap<>();
        Movie movieCreated;
        URI location;

        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(error -> validations.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(validations);
        }

        movieCreated = movieService.save(movie);
        location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movieCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(movieCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody Movie movie, BindingResult result) {
        Map<String, Object> validations = new HashMap<>();
        Movie movieUpdated;
        URI location;

        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(error -> validations.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(validations);
        }

        movieUpdated = movieService.update(id, movie);
        location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movieUpdated.getId())
                .toUri();

        return ResponseEntity.created(location).body(movieUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        movieService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
