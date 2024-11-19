package se.ifmo.ru.first_service.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import se.ifmo.ru.first_service.models.Movie;
import se.ifmo.ru.first_service.models.MpaaRating;

public interface MovieService {
    List<Movie> getMovies();

    Movie getMovie(@PathVariable Integer id);

    List<Movie> getMoviesByOscars(@RequestParam Long oscarsCount);

    List<Movie> getMoviesByName(@RequestParam String substr);

    Movie addMovie(@RequestBody Movie movie);

    Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie);

    boolean deleteMovie(@PathVariable Integer id);

    boolean deleteMovieByRating(@RequestParam MpaaRating mpaaRating);

    boolean awardMoviesByRating();

    List<Movie> awardMoviesByOscarsAndDuration(@PathVariable int minLingth, @RequestParam long oscarsCount);
}
