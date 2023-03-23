package ru.tinkoff.edu.java.linkparser.result;

public record GitHubResult(String username, String repository) implements Result {
}