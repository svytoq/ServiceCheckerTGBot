package ru.tinkoff.edu.java.bot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageProcessor {

    public SendMessage process(Update update, String textToSend){
        SendMessage message = new SendMessage();
        long chatId = update.getMessage().getChatId();
        message.setChatId(chatId);
        message.setText(textToSend);
        return message;
    }
}
