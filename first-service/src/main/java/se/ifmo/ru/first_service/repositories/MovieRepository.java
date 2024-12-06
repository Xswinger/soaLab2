package se.ifmo.ru.first_service.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.ifmo.ru.first_service.models.Movie;
import se.ifmo.ru.first_service.models.MpaaRating;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>  {

    Page<Movie> findByIdOrNameOrCreationDateOrOscarCountOrLengthOrBudgetOrTotalBoxOfficeOrMpaaRating(Long id, String name, ZonedDateTime creationDate, Integer oscarCount, int length, int budget, int totalBoxOffice, MpaaRating mpaaRating, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.oscarCount < :oscarCount")
    Page<Movie> findByOscarCount(Integer oscarCount, Pageable pageable);

    Page<Movie> findByName(String substr, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.id = :id")
    Movie getMovieById(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Movie m WHERE m.id = :id")
    void deleteMovieById(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Movie m WHERE m.mpaaRating = :rating")
    void deleteMovieByRating(MpaaRating rating);

    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.oscarCount = m.oscarCount + :oscarsCount WHERE m.length > :minLength")
    void awardMoviesByOscarsAndDuration(int minLength, long oscarsCount);

    @Query("SELECT m FROM Movie m WHERE m.length > :minLength")
    List<Movie> getMoviesWithLengthGreaterThan(int minLength);

    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.oscarCount = m.oscarCount + 1 WHERE m.mpaaRating = 'R'")
    void awardMoviesByRating();
}
