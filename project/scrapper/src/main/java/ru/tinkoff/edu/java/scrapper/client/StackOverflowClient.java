package ru.tinkoff.edu.java.scrapper.client;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.StackOverflowResponse;

public class StackOverflowClient {
    private final WebClient webClient;

    public StackOverflowClient( WebClient webClient){
        this.webClient = webClient;
    }

    StackOverflowResponse fetchQuestion(long id){
        return webClient
                .get()
                .uri("/questions/{id}", id)
                .retrieve()
                .bodyToMono(StackOverflowResponse.class)
                .block();
    }
}
