package ru.tinkoff.edu.java.bot.dto;

import java.util.ArrayList;
import java.util.List;

public record LinkUpdateRequest(long id, String url, String description, List<Long> tgChatsId) {
}
