package ru.tinkoff.edu.java.bot.command;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.scrapper.response.LinkResponse;
import ru.tinkoff.edu.java.bot.service.LinkSearcher;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ListCommandTest {

    @Mock
    LinkSearcher linkSearcher;

    ListCommand listCommand;

    @BeforeEach
    void beforeStart(){
        listCommand = new ListCommand(new MessageProcessor(), linkSearcher);
    }


    @Test
    void handle_ReturnMessageNoLinks_EmptyLinkList() {
        long id = new Random().nextLong();

        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", id);
        Message message = new Message();
        ReflectionTestUtils.setField(message, "chat", chat);
        Update update = new Update();
        ReflectionTestUtils.setField(update, "message", message);


        when(linkSearcher.getAllLinks(id)).thenReturn(List.of());

        SendMessage request = listCommand.execute(update);

        assertThat(request.getText()).isEqualTo("You don't have any tracked links at the moment");
    }

    @Test
    void handle_ReturnMessageLinksInfo_NotEmptyLinkList() {

        long id = new Random().nextLong();
        URI link = URI.create("https://se.ifmo.ru");


        List<LinkResponse> links = new ArrayList<>();
        links.add(new LinkResponse(id, link));


        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", id);
        Message message = new Message();
        ReflectionTestUtils.setField(message, "chat", chat);
        Update update = new Update();
        ReflectionTestUtils.setField(update, "message", message);

        when(linkSearcher.getAllLinks(id)).thenReturn(links);


        SendMessage request = listCommand.execute(update);


        assertThat(request.getText()).isEqualTo("Tracked links:\n\n" + "https://se.ifmo.ru\n\n");
    }
}
