package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JdbcChatRepository implements ChatRepository {

    private static final String ADD_SQL = """
            INSERT INTO chat(id, user_name) VALUES (?, ?)
            """;

    private static final String FIND_ALL_SQL = """
            SELECT * FROM chat;
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM chat WHERE id = ?
            """;

    private static final String SELECT_BY_ID_SQL = """
            SELECT * FROM chat WHERE id = ?
            """;

    private static final String SELECT_BY_URL_SQL = """
            SELECT * FROM chat WHERE URL = ?
            """;


    private final JdbcTemplate jdbcTemplate;

    @Override
    public Chat add(Chat chat) {
        this.jdbcTemplate.update(ADD_SQL, chat.getId(), chat.getUsername());
        return findById(chat.getId()).get();
    }

    @Override
    public List<Chat> findAll() {
        return this.jdbcTemplate.query(FIND_ALL_SQL, new BeanPropertyRowMapper<>(Chat.class));
    }

    @Override
    public boolean removeById(long id) {
        return (jdbcTemplate.update(DELETE_BY_ID_SQL, id) >= 1);
    }


    @Override
    public Optional<Chat> findById(long id) {
        return this.jdbcTemplate.query(SELECT_BY_ID_SQL, new BeanPropertyRowMapper<>(Chat.class), id).stream().findFirst();
    }

    @Override
    public Optional<Chat> findByUrl(String url) {
        return this.jdbcTemplate.query(SELECT_BY_URL_SQL, new BeanPropertyRowMapper<>(Chat.class), url).stream().findFirst();
    }
}
