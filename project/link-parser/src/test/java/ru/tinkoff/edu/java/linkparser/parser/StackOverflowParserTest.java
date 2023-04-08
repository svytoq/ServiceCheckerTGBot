package ru.tinkoff.edu.java.linkparser.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkparser.result.GitHubResult;
import ru.tinkoff.edu.java.linkparser.result.Result;
import ru.tinkoff.edu.java.linkparser.result.StackOverflowResult;

import static org.assertj.core.api.Assertions.assertThat;

public class StackOverflowParserTest {
    LinkParserChain linkParserChain;

    @BeforeEach
    public void beforeStart(){
        linkParserChain = LinkParserBuilder.build();
    }

    @Test
    void handle_StackOverflow_CorrectlyLink(){
        String link = "https://stackoverflow.com/questions/1510672";

        StackOverflowResult stackOverflowResult = new StackOverflowResult("1510672");



        assertThat(linkParserChain.parseLink(link)).isEqualTo(stackOverflowResult);
    }

    @Test
    void handle_StackOverflow_InCorrectlyLink(){
        String link = "https://ru.stackoverflow.com/questions";


        assertThat(linkParserChain.parseLink(link)).isNull();

    }
}
