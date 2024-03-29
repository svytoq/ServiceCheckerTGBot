package ru.tinkoff.edu.java.bot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.LinkResponse;

import java.util.List;

@Component
@AllArgsConstructor
public class LinkSearcher {
    private final ScrapperClient scrapperClient;

    public List<LinkResponse> getAllLinks(long chatId) {
        return scrapperClient.getAllLinks(chatId).links();
    }
}
