package ru.tinkoff.edu.java.linkparser.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkparser.result.GitHubResult;


import static org.assertj.core.api.Assertions.assertThat;
public class GitHubLinkParserTest {

    LinkParserChain linkParserChain;

    @BeforeEach
    public void beforeStart(){
        linkParserChain = LinkParserBuilder.build();
    }

    @Test
    void handle_GithubResult_CorrectlyLink(){
        String link = "https://github.com/svytoq/ServiceCheckerTGBot";

        GitHubResult gitHubResult = new GitHubResult("svytoq", "ServiceCheckerTGBot" );

        System.out.println(linkParserChain.parseLink(link));
        assertThat(linkParserChain.parseLink(link)).isEqualTo(gitHubResult);
    }

    @Test
    void handle_GithubResult_InCorrectlyLink(){
        String link = "https://github.com/svytoq";


        assertThat(linkParserChain.parseLink(link)).isNull();

    }

}
