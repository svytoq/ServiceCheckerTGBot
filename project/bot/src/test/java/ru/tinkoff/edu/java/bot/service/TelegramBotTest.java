package ru.tinkoff.edu.java.bot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.tinkoff.edu.java.bot.command.Command;
import ru.tinkoff.edu.java.bot.command.CommandName;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelegramBotTest {

    TelegramBot telegramBot;

    @Mock
    ApplicationConfig applicationConfig;

    @Mock
    MessageProcessor messageProcessor;

    @Mock
    List<BotCommand> listOfBotCommans;

    @Mock
    Map<CommandName, Command> mapOfCommands;

    @BeforeEach
    public void beforeStart(){
        telegramBot = new TelegramBot(applicationConfig, messageProcessor);
    }

    @Test
    void handle_executeInvalidCommandSendMessageRequest() {

        long id = new Random().nextLong();
        String helpMessage = "the command is unknown, enter the /help command to see the available commands";
        CommandName commandName = CommandName.INVALID_COMMAND;

        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", id);
        Message message = new Message();
        ReflectionTestUtils.setField(message, "chat", chat);
        Update update = new Update();
        ReflectionTestUtils.setField(update, "message", message);
        //чет не додумался как красиво сделать, видимо стоит немного менять логику бота))), но дедлайн уже близко, проверил просто команду в InvalidCommandTest
        SendMessage request = ReflectionTestUtils.invokeMethod(telegramBot, "executeCommand", commandName, update);
        assertThat(request.getText()).isEqualTo("the command is unknown, enter the /help command to see the available commands");
    }
}
