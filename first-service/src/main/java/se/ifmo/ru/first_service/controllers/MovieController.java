package se.ifmo.ru.first_service.controllers;


import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getMovies() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getMovies());
    }

    @GetMapping("/{id}")
    @WithRateLimitProtection
    public ResponseEntity<?> getMovie(@PathVariable @Size(min = 0, message = "Invalid input") Integer id) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getMovie(id));
    }

    @GetMapping("/oscars")
    @WithRateLimitProtection
    public ResponseEntity<?> getMoviesByOscars(@RequestParam @Min(value = 0, message = "Invalid input") Long oscarsCount) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getMoviesByOscars(oscarsCount));
    }

    @GetMapping("/name")
    @WithRateLimitProtection
    public ResponseEntity<?> getMoviesByName(@RequestParam String substr) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getMoviesByName(substr));
    }

    @PostMapping
    @WithRateLimitProtection
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie addedMovie = this.service.addMovie(movie);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(addedMovie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }

    @PutMapping("/{id}")
    @WithRateLimitProtection
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        try {
            Movie updatedMovie = this.service.updateMovie(id, movie);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(updatedMovie);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
        }
    }

    @DeleteMapping("/{id}")
    @WithRateLimitProtection
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        //TODO strange logic, change in future
        // try {
            boolean isMovieDelete = this.service.deleteMovie(id);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(isMovieDelete);
        // } catch (Exception e) {
        //     Response response = new Response();
        //     response.setStatusCode("404");
        //     response.setStatusMsg("Movie not found");
        //     return ResponseEntity
        //         .status(HttpStatus.NOT_FOUND)
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .body(response);
        // }
    }

    @DeleteMapping("/rating")
    @WithRateLimitProtection
    public ResponseEntity<?> deleteMovieByRating(@RequestParam MpaaRating mpaaRating) {
        //TODO strange logic, change in future
        // try {
            boolean isMovieDelete = this.service.deleteMovieByRating(mpaaRating);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(isMovieDelete);
        // } catch (Exception e) {
        //     Response response = new Response();
        //     response.setStatusCode("404");
        //     response.setStatusMsg("Movie not found");
        //     return ResponseEntity
        //         .status(HttpStatus.NOT_FOUND)
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .body(response);
        // }
    }

    @GetMapping("/reward-r")
    public ResponseEntity<?> addMoviesOscar() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.awardMoviesByRating());
    }

    @PostMapping("/honor-by-length/{min-length}/oscars-to-add")
    public ResponseEntity<?> awardMoviesByOscarsAndDuration(@PathVariable("min-length") int minLingth, @RequestParam long oscarsCount) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.awardMoviesByOscarsAndDuration(minLingth, oscarsCount));
    }
}
