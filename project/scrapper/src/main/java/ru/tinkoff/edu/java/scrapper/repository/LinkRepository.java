package ru.tinkoff.edu.java.scrapper.repository;

import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;

import java.util.List;
import java.util.Optional;

public interface LinkRepository {
    Link add(Link link);
    boolean removeById(long id);
    List<Link> findAll();
    Optional<Link> findById(long id);
    Optional<Link> findByUrl(String url);

    List<Link> findUpdatedLongTimeAgo(Long sec);
}
