package ru.tinkoff.edu.java.bot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.command.*;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.enums.CommandName;


import java.util.*;


import static ru.tinkoff.edu.java.bot.enums.CommandName.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger logger = LogManager.getLogger();

    final private ApplicationConfig applicationConfig;
    final private MessageProcessor messageProcessor;
    final private List<BotCommand> listOfBotCommands;
    final private CommandChain commandChain;

    public TelegramBot(ApplicationConfig applicationConfig, MessageProcessor messageProcessor){
        this.applicationConfig = applicationConfig;
        this.messageProcessor = messageProcessor;
        this.listOfBotCommands = new ArrayList<>();
        this.commandChain = CommandChainBuilder.build("http://localhost:3000/");

        CommandChain nowChain = commandChain;
        while (nowChain != null){
            if(nowChain.getCommandName() != INVALID_COMMAND){
            listOfBotCommands.add(nowChain.toApiCommand());
            }
            nowChain = nowChain.getNextCommandChain();
        }

        try {
            this.execute(new SetMyCommands(listOfBotCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            logger.error("something want wrong");
        }
    }

    @Override
    public String getBotUsername(){
       return applicationConfig.name();
    }

    @Override
    public String getBotToken(){
        return applicationConfig.token();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();

            CommandName command = readCommand(messageText);

            SendMessage message = executeCommand(command, update);
            sendMessage(message);
        }
    }

    public SendMessage executeCommand(CommandName commandName, Update update){
        return commandChain.process(commandName, update);
    }

    private void sendMessage(SendMessage message){
        try {
            execute(message);
        }catch (TelegramApiException e) {
            logger.error("something want wrong");
        }
    }

    private CommandName readCommand(String text){
        CommandName commandName;
        try {
            commandName = CommandName.valueOf(text.substring(1).toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e){
            commandName = CommandName.INVALID_COMMAND;
        }
        return commandName;
    }

}
