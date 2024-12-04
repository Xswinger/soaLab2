package se.ifmo.ru.first_service.controllers;


import java.time.ZonedDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.ifmo.ru.first_service.annotations.WithRateLimitProtection;
import se.ifmo.ru.first_service.models.Movie;
import se.ifmo.ru.first_service.models.MpaaRating;
import se.ifmo.ru.first_service.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    @WithRateLimitProtection
    public ResponseEntity<?> getMovies(
        @PathVariable(required = false) Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) ZonedDateTime creationDate,
        @RequestParam(required = false) Integer oscarCount,
        @RequestParam(required = false) Integer length,
        @RequestParam(required = false) Integer budget,
        @RequestParam(required = false) Integer totalBoxOffice,
        @RequestParam(required = false) MpaaRating mpaaRating,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getMovies(id, name, creationDate, oscarCount, length, budget, totalBoxOffice, mpaaRating, page, pageSize, sort));
    }

    @GetMapping("/{id}")
    @WithRateLimitProtection
    public ResponseEntity<?> getMovie(@PathVariable @Size(min = 0, message = "Invalid input") Integer id) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getMovie(id));
    }

    @GetMapping("/oscars")
    @WithRateLimitProtection
    public ResponseEntity<?> getMoviesByOscars(
        @RequestParam @Min(value = 0, message = "Invalid input") Integer oscarsCount,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "asc") String sort
    ) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getMoviesByOscars(oscarsCount, page, pageSize, sort));
    }

    @GetMapping("/name")
    @WithRateLimitProtection
    public ResponseEntity<?> getMoviesByName(
        @RequestParam String substr,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "asc") String sort
    ) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getMoviesByName(substr, page, pageSize, sort));
    }

    @PostMapping
    @WithRateLimitProtection
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        Movie addedMovie = this.service.addMovie(movie);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(addedMovie);
        
    }

    @PutMapping("/{id}")
    @WithRateLimitProtection
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        Movie updatedMovie = this.service.updateMovie(id, movie);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(updatedMovie);
    }

    @DeleteMapping("/{id}")
    @WithRateLimitProtection
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        this.service.deleteMovie(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
    }

    @DeleteMapping("/rating")
    @WithRateLimitProtection
    public ResponseEntity<?> deleteMovieByRating(@RequestParam MpaaRating mpaaRating) {
        this.service.deleteMovieByRating(mpaaRating);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
    }

    @GetMapping("/reward-r")
    public ResponseEntity<?> addMoviesOscar() {
        this.service.awardMoviesByRating();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
    }

    @PostMapping("/honor-by-length/{min-length}/oscars-to-add")
    public ResponseEntity<?> awardMoviesByOscarsAndDuration(@PathVariable("min-length") int minLingth, @RequestParam long oscarsCount) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.awardMoviesByOscarsAndDuration(minLingth, oscarsCount));
    }
}
