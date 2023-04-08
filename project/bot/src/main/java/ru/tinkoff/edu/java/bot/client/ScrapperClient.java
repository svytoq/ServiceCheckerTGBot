package ru.tinkoff.edu.java.bot.client;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.scrapper.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.scrapper.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.ListLinksResponse;

@Component
public class ScrapperClient {
    private final String TG_CHAT_ENDPOINT = "/tg-chat/{chatId}";
    private final String TG_CHAT_HEADER = "Tg-Chat-Id";
    private final String LINK_ENDPOINT = "/link";
    private final String DEFAULT_URL = "http://localhost:3000/";
    private final WebClient webClient;

    public ScrapperClient() {
        this.webClient = WebClient.create(DEFAULT_URL);
    }
    public ScrapperClient(String Url) {
        this.webClient = WebClient.create(Url);
    }

    public LinkResponse trackLink(String link, long chatId) {
        return webClient
                .post()
                .uri(LINK_ENDPOINT)
                .header(TG_CHAT_HEADER, String.valueOf(chatId))
                .body(BodyInserters.fromValue(new AddLinkRequest(link)))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public LinkResponse untrackLink(String link, long chatId) {
        return webClient
                .method(HttpMethod.DELETE)
                .uri(LINK_ENDPOINT)
                .header(TG_CHAT_HEADER, String.valueOf(chatId))
                .body(BodyInserters.fromValue(new RemoveLinkRequest(link)))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public ListLinksResponse getAllLinks(long chatId) {
        return webClient
                .get()
                .uri(LINK_ENDPOINT)
                .header(TG_CHAT_HEADER, String.valueOf(chatId))
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

    public boolean registerChat(long chatId) {
        return Boolean.TRUE.equals(webClient
                .post()
                .uri(TG_CHAT_ENDPOINT , chatId)
                .exchangeToMono(clientResponse -> Mono.just(!clientResponse.statusCode().isError()))
                .block());
    }

    public boolean removeChat(long chatId) {
        return Boolean.TRUE.equals(webClient
                .method(HttpMethod.DELETE)
                .uri(TG_CHAT_ENDPOINT , chatId)
                .exchangeToMono(clientResponse -> Mono.just(!clientResponse.statusCode().isError()))
                .block());
    }

}