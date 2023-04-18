package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.exception.NotFoundException;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class JdbcLinkService implements LinkService {

    private final LinkRepository linkRepository;

    @Transactional
    @Override
    public Link add(long tgChatId, String url) {
        Link link = linkRepository.findByUrl(url).orElseGet(() -> linkRepository.add(new Link(url)));
        return link;
    }

    @Transactional
    @Override
    public Link remove(long tgChatId, String url) {
        Link link = linkRepository.findByUrl(url).orElseThrow(() -> new NotFoundException());
        linkRepository.removeById(link.getId());
        //переделать
        return link;
    }


    @Override
    public List<Link> listAll(long tgChatId) {
        return linkRepository.findAll();
        //переделать
    }
}
