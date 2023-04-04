package ru.tinkoff.edu.java.bot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvalidCommandTest {

    InvalidCommand invalidCommand;

    @BeforeEach
    void beforeStart(){
        invalidCommand = new InvalidCommand(new MessageProcessor());
    }


    @Test
    void handle_ReturnDefaultText_InvalidCommand() {
        long id = new Random().nextLong();

        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", id);
        Message message = new Message();
        ReflectionTestUtils.setField(message, "chat", chat);
        Update update = new Update();
        ReflectionTestUtils.setField(update, "message", message);


        SendMessage request = invalidCommand.execute(update);

        assertThat(request.getText()).isEqualTo("the command is unknown, enter the /help command to see the available commands");
    }

}
