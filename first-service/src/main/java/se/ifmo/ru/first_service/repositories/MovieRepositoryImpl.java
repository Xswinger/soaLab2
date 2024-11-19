// package se.ifmo.ru.first_service.repositories;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;
// import se.ifmo.ru.first_service.models.Movie;

// import java.util.List;

// @Repository
// public class MovieRepositoryImpl implements MovieRepository {

//     private final JdbcTemplate jdbcTemplate;

//     @Autowired
//     public MovieRepositoryImpl(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     @Override
//     public List<Movie> getMovies() {
//         String sql = "SELECT * FROM Movie";
//         List<Movie> movies = jdbcTemplate.queryForList(sql, Movie.class);
//         return movies;
//     }

//     @Override
//     public Movie getMovieById(Integer id) {
//         String sql = "SELECT * FROM Movie WHERE id = ?";
//         return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Movie.class));
//     }

//     @Override
//     public List<Movie> getMoviesByOscarsCount(Long count) {
//         String sql = "SELECT * FROM Movie WHERE oscarCount < ?";
//         return jdbcTemplate.query(sql, new Object[]{count}, new BeanPropertyRowMapper<>(Movie.class));
//     }

//     @Override
//     public List<Movie> getMoviesByName(String substr) {
//         String sql = "SELECT * FROM Movie WHERE name LIKE ?";
//         return jdbcTemplate.query(sql, new Object[]{"%" + substr + "%"}, new BeanPropertyRowMapper<>(Movie.class));
//     }

//     @Override
//     public Movie addMovie(Movie entity) {
//         String sql = "INSERT INTO Movie (name, oscarCount, mpaaRating) VALUES (?, ?, ?)";
//         jdbcTemplate.update(sql, entity.getName(), entity.getOscarsCount(), entity.getMpaaRating());
//         return entity;
//     }

//     @Override
//     public Movie updateMovie(Movie entity) {
//         String sql = "UPDATE Movie SET name = ?, oscarCount = ?, mpaaRating = ? WHERE id = ?";
//         jdbcTemplate.update(sql, entity.getName(), entity.getOscarsCount(), entity.getMpaaRating(), entity.getId());
//         return entity;
//     }

//     @Override
//     public boolean deleteMovieById(Integer id) {
//         String sql = "DELETE FROM Movie WHERE id = ?";
//         return jdbcTemplate.update(sql, id) > 0;
//     }

//     @Override
//     public boolean deleteMovieByRating(String rating) {
//         String sql = "DELETE FROM Movie WHERE mpaaRating = ?";
//         return jdbcTemplate.update(sql, rating) > 0;
//     }
// }