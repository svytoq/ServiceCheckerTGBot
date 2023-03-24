package ru.tinkoff.edu.java.scrapper.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.webClient.StackOverflowResponse;

@RequiredArgsConstructor
public class StackOverflowClient {
    private final WebClient webClient;



    StackOverflowResponse fetchQuestion(long id){
        return webClient
                .get()
                .uri("/questions/{id}", id)
                .retrieve()
                .bodyToMono(StackOverflowResponse.class)
                .block();
    }
}
