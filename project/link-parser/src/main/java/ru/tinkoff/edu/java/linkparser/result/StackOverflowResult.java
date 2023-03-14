package ru.tinkoff.edu.java.linkparser.result;

import java.util.regex.Pattern;

public record StackOverflowResult(String questionId) implements Result {

    private static final Pattern QUESTION_PATTERN = Pattern.compile(
            "^(?:https://)?(?:www\\.)?stackoverflow\\.com/questions/(?<id>\\d+)(?:/?|/[a-zA-Z\\-]+/?)$"
    );
}