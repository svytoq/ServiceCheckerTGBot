package ru.tinkoff.edu.java.scrapper.client;


import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class ClientConfiguration {
    private static final String GITHUB_BASE_URL = "https://api.github.com";
    private static final String STACK_OVERFLOW_BASE_URL = "https://api.stackexchange.com";


    @Bean
    public WebClient githubWebClient() {
        return WebClient.builder()
                .baseUrl(GITHUB_BASE_URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public GitHubClient githubClient(WebClient githubWebClient) {
        return new GitHubClient(githubWebClient);
    }

    @Bean
    public WebClient stackOverflowWebClient() {
        return WebClient.builder()
                .baseUrl(STACK_OVERFLOW_BASE_URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Bean
    public StackOverflowClient stackOverflowClient(WebClient stackOverflowWebClient) {
        return new StackOverflowClient(stackOverflowWebClient);
    }

}
