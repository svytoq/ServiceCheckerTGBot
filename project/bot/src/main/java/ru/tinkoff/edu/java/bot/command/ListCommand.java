package ru.tinkoff.edu.java.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.LinkResponse;
import ru.tinkoff.edu.java.bot.service.LinkSearcher;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

import java.util.ArrayList;
import java.util.List;


public class ListCommand extends AbstractCommand{

    private LinkSearcher linkSearcher;
    static final String LIST_EMPTY = "You don't have any tracked links at the moment";

    public ListCommand(MessageProcessor messageProcessor, LinkSearcher linkSearcher){
        commandName = CommandName.LIST;
        this.messageProcessor = messageProcessor;
        this.linkSearcher = linkSearcher;
    }

    @Override
    public SendMessage execute(Update update) {

        List<LinkResponse> links = linkSearcher.getAllLinks(update.getMessage().getChat().getId());

        if (links.isEmpty()){
            return messageProcessor.process(update, LIST_EMPTY);
        }else {
            String message = "Tracked links:\n\n";
            for (LinkResponse link : links){
                message += link.url() + "\n\n";
            }

            return messageProcessor.process(update, message);
        }
    }

}
