package ru.tinkoff.edu.java.scrapper.dto.webClient;

import java.time.OffsetDateTime;

public record GithubResponse(String name, OffsetDateTime updatedAt) {
}