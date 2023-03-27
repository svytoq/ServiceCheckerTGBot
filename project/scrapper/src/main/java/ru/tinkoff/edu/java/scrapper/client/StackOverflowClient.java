package ru.tinkoff.edu.java.scrapper.client;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.webClient.StackOverflowResponse;


public class StackOverflowClient {
    private WebClient webClient;
    private static final String STACK_OVERFLOW_BASE_URL = "https://api.stackexchange.com";
    private String URL;

    public StackOverflowClient(String customURL){
        URL = customURL;
        webClient = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public StackOverflowClient(){
        URL = STACK_OVERFLOW_BASE_URL;
        webClient = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
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
