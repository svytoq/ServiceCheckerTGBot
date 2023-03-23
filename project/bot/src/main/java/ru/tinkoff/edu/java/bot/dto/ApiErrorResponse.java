package ru.tinkoff.edu.java.bot.dto;

import java.util.ArrayList;
import java.util.List;

public record ApiErrorResponse(String description, String code, String exceptionName, String exceptionMessage, List<String> stacktrace) {
}