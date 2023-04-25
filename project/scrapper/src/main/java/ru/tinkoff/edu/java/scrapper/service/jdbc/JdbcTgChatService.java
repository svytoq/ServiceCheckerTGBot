package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.model.Chat;
import ru.tinkoff.edu.java.scrapper.service.TgChatService;
import ru.tinkoff.edu.java.scrapper.repository.ChatRepository;

@Service
@AllArgsConstructor
public class JdbcTgChatService implements TgChatService {


    private final ChatRepository chatRepository;

    @Transactional
    @Override
    public void register(long chatId) {
        if (chatRepository.findById(chatId).isEmpty()) {
            chatRepository.add(new Chat(chatId));
        }
    }

    @Transactional
    @Override
    public void unregister(long chatId) {
        chatRepository.removeById(chatId);
    }
}
