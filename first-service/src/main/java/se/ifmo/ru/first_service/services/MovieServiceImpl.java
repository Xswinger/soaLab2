package se.ifmo.ru.first_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import se.ifmo.ru.first_service.models.Movie;
import se.ifmo.ru.first_service.models.MpaaRating;
import se.ifmo.ru.first_service.repositories.MovieRepository;
// import se.ifmo.ru.first_service.repositories.MovieRepositoryImpl;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getMovies() {
        List<Movie> movies = this.repository.getMovies();
        // if (movies == null) {
        //     throw new MovieNotFoundException();
        // }
        return movies;
    }

    public Movie getMovie(@PathVariable Integer id) {
        Movie movie = this.repository.getMovieById(id);
        // if (movie == null) {
        //     throw new MovieNotFoundException();
        // }
        return movie;
    }

    public List<Movie> getMoviesByOscars(@RequestParam Long oscarsCount) {
        List<Movie> movies = this.repository.getMoviesByOscarsCount(oscarsCount);
        // if (movies == null) {
        //     throw new MovieNotFoundException();
        // }
        return movies;
    }

    public List<Movie> getMoviesByName(@RequestParam String substr) {
        List<Movie> movies = this.repository.getMoviesByName(substr);
        // if (movies == null) {
        //     throw new MovieNotFoundException();
        // }
        return movies;
    }

    public Movie addMovie(@RequestBody Movie movie) {
        return this.repository.save(movie);
    }

    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        return this.repository.save(movie);
    }

    public boolean deleteMovie(@PathVariable Integer id) {
        return this.repository.deleteMovieById(id) == 1 ? true : false;
    }

    public boolean deleteMovieByRating(@RequestParam MpaaRating mpaaRating) {
        return this.repository.deleteMovieByRating(mpaaRating) > 0 ? true : false;
    }

    public List<Movie> awardMoviesByOscarsAndDuration(@PathVariable int minLength, @RequestParam long oscarsCount) {
        this.repository.awardMoviesByOscarsAndDuration(minLength, oscarsCount);

        return this.repository.getMoviesWithLengthGreaterThan(minLength);
    }

    public boolean awardMoviesByRating() {
        return this.repository.awardMoviesByRating() > 0 ? true : false;
    }
}
