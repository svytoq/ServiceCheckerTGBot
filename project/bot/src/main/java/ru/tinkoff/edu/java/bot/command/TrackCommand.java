package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.enums.CommandName;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

public class TrackCommand extends AbstractCommand{

    public TrackCommand(MessageProcessor messageProcessor){
        commandName = CommandName.TRACK;
        this.messageProcessor = messageProcessor;
    }

    @Override
    public SendMessage execute(Update update) {
        return messageProcessor.process(update, "Type link:");
    }
}
