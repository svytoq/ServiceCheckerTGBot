package ru.tinkoff.edu.java.scrapper.client;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;

@AllArgsConstructor
public class BotClient {

    private static final String BOT_BASE_URL = "http://localhost:8000/api/update";

    private final WebClient webClient;

    public BotClient(String customeURL){
        webClient = WebClient.builder()
                .baseUrl(customeURL)
                .build();
    }

    public BotClient(){
        webClient = WebClient.builder()
                .baseUrl(BOT_BASE_URL)
                .build();
    }


    public void sendUpdate(LinkUpdateRequest linkUpdateRequest) {
        webClient
                .post()
                .uri(BOT_BASE_URL)
                .body(BodyInserters.fromValue(linkUpdateRequest))
                .exchangeToMono(clientResponse -> Mono.just(!clientResponse.statusCode().isError()))
                .block();
    }
}
