package ru.tinkoff.edu.java.scrapper.dto.webClient;

import java.time.OffsetDateTime;

public record StackOverflowResponse(String title, OffsetDateTime updatedAt) {
}