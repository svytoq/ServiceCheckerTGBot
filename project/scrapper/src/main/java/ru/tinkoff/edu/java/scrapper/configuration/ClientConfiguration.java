package ru.tinkoff.edu.java.scrapper.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;

public class ClientConfiguration {

    @Bean("githubClient")
    public GitHubClient githubClient(WebClient githubWebClient) {
        return new GitHubClient();
    }

    @Bean("stackOverflowClient")
    public StackOverflowClient stackOverflowClient() {
        return new StackOverflowClient();
    }

}
