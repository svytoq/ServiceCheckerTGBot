package ru.tinkoff.edu.java.scrapper.dto;

import java.time.OffsetDateTime;

public record GithubResponse(String name, OffsetDateTime updatedAt) {
}