package ru.tinkoff.edu.java.scrapper.client;


import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class ClientConfiguration {
    private static final String GITHUB_BASE_URL = "https://api.github.com";
    private static final String STACK_OVERFLOW_BASE_URL = "https://api.stackexchange.com";


    @Bean("githubWebClient")
    public WebClient githubWebClient() {
        return WebClient.builder()
                .baseUrl(GITHUB_BASE_URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean("githubWebClient")
    public WebClient githubWebClient(String URL) {
        return WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }



    @Bean("githubClient")
    public GitHubClient githubClient(WebClient githubWebClient) {
        return new GitHubClient(githubWebClient);
    }

    @Bean("stackOverflowWebClient")
    public WebClient stackOverflowWebClient() {
        return WebClient.builder()
                .baseUrl(STACK_OVERFLOW_BASE_URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean("stackOverflowWebClient")
    public WebClient stackOverflowWebClient(String URL) {
        return WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }



    @Bean("stackOverflowClient")
    public StackOverflowClient stackOverflowClient(WebClient stackOverflowWebClient) {
        return new StackOverflowClient(stackOverflowWebClient);
    }

}
