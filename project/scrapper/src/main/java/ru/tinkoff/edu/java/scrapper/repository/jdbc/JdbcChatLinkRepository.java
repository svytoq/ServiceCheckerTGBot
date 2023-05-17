package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.ChatLinkRepository;

import java.net.URI;
import java.util.List;

public class JdbcChatLinkRepository implements ChatLinkRepository {
    @Override
    public boolean addLinkToChat(long chatId, Link link) {
        return false;
    }

    @Override
    public boolean deleteLinkFromChat(long chatId, Link link) {
        return false;
    }

    @Override
    public List<Link> findChatLinks(long chatId) {
        return null;
    }

    @Override
    public List<Chat> findLinkChats(URI link) {
        return null;
    }
}
