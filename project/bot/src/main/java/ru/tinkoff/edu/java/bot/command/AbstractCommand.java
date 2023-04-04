package ru.tinkoff.edu.java.bot.command;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

@Getter
public abstract class AbstractCommand implements Command{

    protected CommandName commandName;
    protected MessageProcessor messageProcessor;

    @Override
    public BotCommand toApiCommand() {
        return new BotCommand(commandName.getCommandName(), commandName.getCommandDescription());
    }

    @Override
    abstract public SendMessage execute(Update update);
}
