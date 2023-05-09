package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JdbcLinkRepository implements LinkRepository {

    private static final String ADD_SQL = """
            INSERT INTO link(id, url, update_time) VALUES (?, ?, ?)
            """;

    private static final String FIND_ALL_SQL = """
            SELECT * FROM link;
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM link WHERE id = ?
            """;

    private static final String SELECT_BY_ID_SQL = """
            SELECT * FROM link WHERE id = ?
            """;

    private static final String SELECT_BY_URL_SQL = """
            SELECT * FROM link WHERE URL = ?
            """;

    private static final String SELECT_BY_UPDATE_TIME = """
            SELECT * FROM link WHERE update_time < ?
            """;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Link add(Link link) {
            jdbcTemplate.update(ADD_SQL, link.getUrl());
            return findByUrl(link.getUrl()).get();
    }
    @Override
    public List<Link> findAll() {
        return this.jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Link.class));
    }

    @Override
    public boolean removeById(long id) {
        return (jdbcTemplate.update(DELETE_BY_ID_SQL, id) >= 1);
    }

    @Override
    public Optional<Link> findById(long id) {
        return this.jdbcTemplate.query(SELECT_BY_ID_SQL, new BeanPropertyRowMapper<>(Link.class), id).stream().findFirst();
    }

    @Override
    public Optional<Link> findByUrl(String url) {
        return this.jdbcTemplate.query(SELECT_BY_URL_SQL, new BeanPropertyRowMapper<>(Link.class), url).stream().findFirst();
    }

    @Override
    public List<Link> findUpdatedLongTimeAgo(Long sec) {
        Instant seconds = Instant.now().minusSeconds(sec);
        return this.jdbcTemplate.query(SELECT_BY_UPDATE_TIME, new BeanPropertyRowMapper<>(Link.class), seconds);
    }
}
