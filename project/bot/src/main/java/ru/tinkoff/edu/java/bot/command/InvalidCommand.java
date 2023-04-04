package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

public class InvalidCommand extends AbstractCommand{

    static final String DEFAULT_TEXT = "the command is unknown, enter the /help command to see the available commands";

    public InvalidCommand(MessageProcessor messageProcessor){
        commandName = CommandName.INVALID_COMMAND;
        this.messageProcessor = messageProcessor;
    }

    @Override
    public SendMessage execute(Update update) {
        SendMessage sendMessage = messageProcessor.process(update, DEFAULT_TEXT);
        return sendMessage;
    }

}
