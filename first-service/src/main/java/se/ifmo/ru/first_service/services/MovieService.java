package se.ifmo.ru.first_service.services;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import se.ifmo.ru.first_service.dto.MovieResponseArray;
import se.ifmo.ru.first_service.models.Movie;
import se.ifmo.ru.first_service.models.MpaaRating;

public interface MovieService {
    MovieResponseArray getMovies(
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
    );

    Movie getMovie(@PathVariable Integer id);

    MovieResponseArray getMoviesByOscars(
        @RequestParam Integer oscarsCount, 
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "asc") String sort
    );

    MovieResponseArray getMoviesByName(
        @RequestParam String substr, 
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "asc") String sort
    );

    Movie addMovie(@RequestBody Movie movie);

    Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie);

    void deleteMovie(@PathVariable Integer id);

    void deleteMovieByRating(@RequestParam MpaaRating mpaaRating);

    void awardMoviesByRating();

    List<Movie> awardMoviesByOscarsAndDuration(@PathVariable int minLingth, @RequestParam long oscarsCount);
}
