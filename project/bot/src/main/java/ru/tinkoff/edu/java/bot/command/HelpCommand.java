package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.enums.CommandName;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

public class HelpCommand extends AbstractCommand{

    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities\n\n " +
            "You can execute commands from the main menu on the left side or by typing a command\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /help to see data stored about yourself\n\n" +
            "Type ....";


    public HelpCommand(MessageProcessor messageProcessor){
        commandName = CommandName.HELP;
        this.messageProcessor = messageProcessor;
    }

    @Override
    public SendMessage execute(Update update) {
        return messageProcessor.process(update, HELP_TEXT);
    }
}
