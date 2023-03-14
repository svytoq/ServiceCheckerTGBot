package ru.tinkoff.edu.java.linkparser.parser;

import ru.tinkoff.edu.java.linkparser.result.GitHubResult;
import ru.tinkoff.edu.java.linkparser.result.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHubLinkParser implements LinkParserChain{

    private LinkParserChain nextChain;


    private static final String USERNAME_REGEX = "[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d]))";
    private static final String REPOSITORY_NAME_REGEX = "[a-zA-Z\\d._\\-]";

    private static final Pattern USERNAME_REPOSITORY_PATTERN = Pattern.compile(
            "^(?:https://)?(?:www\\.)?github\\.com" + "/" +
                    "(?<username>" + USERNAME_REGEX + ")" + "/" +
                    "(?<repository>" + REPOSITORY_NAME_REGEX + "+)/?$"
    );

    @Override
    public Result parseLink(String link) {
        Matcher matcher = USERNAME_REPOSITORY_PATTERN.matcher(link);
        if(matcher.matches()){
            return new GitHubResult(matcher.group("username"), matcher.group("repository"));
        }
        else if(!(nextChain == null)){
            return nextChain.parseLink(link);
        }else {
            return null;
        }
    }

    @Override
    public void setNextChain(LinkParserChain nextChain) {
        this.nextChain = nextChain;
    }
}
