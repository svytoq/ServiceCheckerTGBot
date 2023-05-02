package ru.tinkoff.edu.java.scrapper.repository;

import ru.tinkoff.edu.java.scrapper.model.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {
    Chat add (Chat chat);
    boolean removeById(long id);
    List<Chat> findAll();
    Optional<Chat> findById(long id);
    Optional<Chat> findByUrl(String url);
}
