package ru.tinkoff.edu.java.scrapper.dto;

import java.time.OffsetDateTime;

public record StackOverflowResponse(String title, OffsetDateTime updatedAt) {
}