package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.tinkoff.edu.java.bot.enums.CommandName;

public interface CommandChain {
    CommandName getCommandName();
    BotCommand toApiCommand();
    SendMessage execute(Update update);
    void setNextCommandChain(CommandChain commandChain);
    CommandChain getNextCommandChain();
    SendMessage process(CommandName commandName, Update update);
}
