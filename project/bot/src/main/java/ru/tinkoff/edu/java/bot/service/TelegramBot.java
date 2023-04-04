package ru.tinkoff.edu.java.bot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.command.*;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;


import java.util.*;


import static ru.tinkoff.edu.java.bot.command.CommandName.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger logger = LogManager.getLogger();

    final private ApplicationConfig applicationConfig;
    final private MessageProcessor messageProcessor;
    final private List<BotCommand> listOfBotCommands;
    final private Map<CommandName, Command> mapOfCommands;

    public TelegramBot(ApplicationConfig applicationConfig, MessageProcessor messageProcessor){
        this.applicationConfig = applicationConfig;
        this.messageProcessor = messageProcessor;
        this.mapOfCommands = new HashMap<>();
        listOfBotCommands = new ArrayList<>();

        mapOfCommands.put(HELP, new HelpCommand(messageProcessor));
        mapOfCommands.put(START,new StartCommand(messageProcessor));
        mapOfCommands.put(TRACK ,new TrackCommand(messageProcessor));
        mapOfCommands.put(UNTRACK,new UntrackCommand(messageProcessor));
        mapOfCommands.put(LIST ,new ListCommand(messageProcessor, new LinkSearcher(new ScrapperClient("http://localhost:3000/"))));
        mapOfCommands.put(INVALID_COMMAND, new InvalidCommand(messageProcessor));

        listOfBotCommands.addAll(mapOfCommands.values().stream().filter( x -> x.getCommandName() != INVALID_COMMAND).map(Command::toApiCommand).toList());
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
        return mapOfCommands.get(commandName).execute(update);
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
