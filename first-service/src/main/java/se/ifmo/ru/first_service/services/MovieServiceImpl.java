package se.ifmo.ru.first_service.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import se.ifmo.ru.first_service.dto.MovieResponseArray;
import se.ifmo.ru.first_service.models.Movie;
import se.ifmo.ru.first_service.models.MpaaRating;
import se.ifmo.ru.first_service.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieResponseArray getMovies(
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
        List<Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(_sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, _sort[0]));
            }
        } else {
            orders.add(new Order(sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sort[0]));
        }

        List<Movie> movies;
        Pageable pagingSort = PageRequest.of(page, pageSize, Sort.by(orders));
        Page<Movie> moviePages;

        if (id != null || name != null || oscarCount != null || length != null || budget != null || totalBoxOffice != null || mpaaRating != null)
            moviePages = repository.findByIdOrNameOrCreationDateOrOscarCountOrLengthOrBudgetOrTotalBoxOfficeOrMpaaRating(id, name, creationDate, oscarCount, length, budget, totalBoxOffice, mpaaRating, pagingSort);
        else
            moviePages = repository.findAll(pagingSort);

        movies = moviePages.getContent();

        MovieResponseArray response = new MovieResponseArray();
        response.setMovies(movies);
        response.setTotalElements(moviePages.getTotalElements());
        response.setTotalPages(moviePages.getTotalPages());

        return response;
    }

    public Movie getMovie(@PathVariable Integer id) {
        Movie movie = this.repository.getMovieById(id);
        return movie;
    }

    public MovieResponseArray getMoviesByOscars(
        @RequestParam Integer oscarsCount, 
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "asc") String sort
    ) {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(sort.contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "oscarCount"));

        List<Movie> movies;
        Pageable pagingSort = PageRequest.of(page, pageSize, Sort.by(orders));
        Page<Movie> moviePages;

        moviePages = repository.findByOscarCount(oscarsCount, pagingSort);

        movies = moviePages.getContent();

        MovieResponseArray response = new MovieResponseArray();
        response.setMovies(movies);
        response.setTotalElements(moviePages.getTotalElements());
        response.setTotalPages(moviePages.getTotalPages());

        return response;
    }

    public MovieResponseArray getMoviesByName(
        @RequestParam String substr,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "asc") String sort    
    ) {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(sort.contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "name"));

        List<Movie> movies;
        Pageable pagingSort = PageRequest.of(page, pageSize, Sort.by(orders));
        Page<Movie> moviePages;

        moviePages = this.repository.findByName(substr, pagingSort);
        movies = moviePages.getContent();

        MovieResponseArray response = new MovieResponseArray();
        response.setMovies(movies);
        response.setTotalElements(moviePages.getTotalElements());
        response.setTotalPages(moviePages.getTotalPages());

        return response;
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
