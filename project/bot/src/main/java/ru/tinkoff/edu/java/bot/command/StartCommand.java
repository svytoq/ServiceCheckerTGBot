package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.enums.CommandName;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

public class StartCommand extends AbstractCommand{

    static final String START_TEXT = " with this bot, you will be able to track changes \n\n" +
            "to github and stackoverflow, for details, enter the command /help";


    public StartCommand(MessageProcessor messageProcessor){
        commandName = CommandName.START;
        this.messageProcessor = messageProcessor;
    }

    @Override
    public SendMessage execute(Update update) {
        String message ="Hi, " + update.getMessage().getChat().getFirstName() +  START_TEXT;
        return messageProcessor.process(update, message);
    }
}
