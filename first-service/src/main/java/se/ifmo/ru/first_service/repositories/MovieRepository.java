package se.ifmo.ru.first_service.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.ifmo.ru.first_service.models.Movie;
import se.ifmo.ru.first_service.models.MpaaRating;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>  {

    @Query("SELECT m FROM Movie m")
    List<Movie> getMovies();

    @Query("SELECT m FROM Movie m WHERE m.id = :id")
    Movie getMovieById(Integer id);

    @Query("SELECT m FROM Movie m WHERE m.oscarCount < :count")
    List<Movie> getMoviesByOscarsCount(Long count);

    @Query("SELECT m FROM Movie m WHERE m.name LIKE :substr")
    List<Movie> getMoviesByName(String substr);

    @Modifying
    @Transactional
    @Query("DELETE FROM Movie m WHERE m.id = :id")
    int deleteMovieById(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Movie m WHERE m.mpaaRating = :rating")
    int deleteMovieByRating(MpaaRating rating);

    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.oscarCount = m.oscarCount + :oscarsCount WHERE m.length > :minLength")
    void awardMoviesByOscarsAndDuration(int minLength, long oscarsCount);

    @Query("SELECT m FROM Movie m WHERE m.length > :minLength")
    List<Movie> getMoviesWithLengthGreaterThan(int minLength);

    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.oscarCount = m.oscarCount + 1 WHERE m.mpaaRating = 'R'")
    int awardMoviesByRating();
}
