package ru.tinkoff.edu.java.scrapper.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.webClient.GithubResponse;

@RequiredArgsConstructor
public class GitHubClient {
    private final WebClient webClient;


    public GithubResponse fetchRepository(String user, String repo){
        return webClient
                .get()
                .uri("/repos/{user}/{repo}", user, repo)
                .retrieve()
                .bodyToMono(GithubResponse.class)
                .block();
    }
}
