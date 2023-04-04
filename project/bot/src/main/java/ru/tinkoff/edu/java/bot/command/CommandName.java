package ru.tinkoff.edu.java.bot.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandName{
    START("/start", "register a user"),
    HELP("/help", "show a window with commands"),
    TRACK("/track", "start tracking links"),
    UNTRACK("/untrack", "stop tracking links"),
    LIST("/list", "show a list of tracked links"),
    INVALID_COMMAND("/", "invalid command");
    private final String commandName;
    private final String commandDescription;
}
