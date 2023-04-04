package ru.tinkoff.edu.java.bot.client;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.bot.dto.scrapper.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.scrapper.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.ListLinksResponse;

@AllArgsConstructor
public class ScrapperClient {
    private final String TG_CHAT_ENDPOINT = "/tg-chat/%s";
    private final String TG_CHAT_HEADER = "Tg-Chat-Id";

    private final WebClient webClient;

    public ScrapperClient(String Url) {
        this.webClient = WebClient.create(Url);
    }

    public LinkResponse trackLink(String link, long chatId) {
        return webClient
                .post()
                .uri("/link")
                .header("Tg-Chat-Id", String.valueOf(chatId))
                .body(BodyInserters.fromValue(new AddLinkRequest(link)))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public LinkResponse untrackLink(String link, long chatId) {
        return webClient
                .method(HttpMethod.DELETE)
                .uri("/link")
                .header("Tg-Chat-Id", String.valueOf(chatId))
                .body(BodyInserters.fromValue(new RemoveLinkRequest(link)))
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public ListLinksResponse getAllLinks(long chatId) {
        return webClient
                .get()
                .uri("/link")
                .header("Tg-Chat-Id", String.valueOf(chatId))
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

    public boolean registerChat(long chatId) {
        return Boolean.TRUE.equals(webClient
                .post()
                .uri("/tg-chat/{chatId}" , chatId)
                .exchangeToMono(clientResponse -> Mono.just(!clientResponse.statusCode().isError()))
                .block());
    }

    public boolean removeChat(long chatId) {
        return Boolean.TRUE.equals(webClient
                .method(HttpMethod.DELETE)
                .uri("/tg-chat/{chatId}" , chatId)
                .exchangeToMono(clientResponse -> Mono.just(!clientResponse.statusCode().isError()))
                .block());
    }

}