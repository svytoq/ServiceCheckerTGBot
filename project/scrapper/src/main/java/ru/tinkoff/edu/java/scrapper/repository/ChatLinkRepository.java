package ru.tinkoff.edu.java.scrapper.repository;

import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.model.Link;

import java.net.URI;
import java.util.List;

public interface ChatLinkRepository {


    boolean addLinkToChat(long chatId, Link link);

    boolean deleteLinkFromChat(long chatId, Link link);


    List<Link> findChatLinks(long chatId);

    List<Chat> findLinkChats(URI link);
}
