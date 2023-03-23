package ru.tinkoff.edu.java.linkparser.parser;

import ru.tinkoff.edu.java.linkparser.result.GitHubResult;
import ru.tinkoff.edu.java.linkparser.result.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHubLinkParser extends AbstractLinkParser{

    private LinkParserChain nextChain;


    private static final String USERNAME_REGEX = "[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d]))";
    private static final String REPOSITORY_NAME_REGEX = "[a-zA-Z\\d._\\-]";

    private static final Pattern USERNAME_REPOSITORY_PATTERN = Pattern.compile(
            "^(?:https://)?(?:www\\.)?github\\.com" + "/" +
                    "(?<username>" + USERNAME_REGEX + ")" + "/" +
                    "(?<repository>" + REPOSITORY_NAME_REGEX + "+)/?$"
    );

    @Override
    public Matcher getNewMatcher(String link) {
        return USERNAME_REPOSITORY_PATTERN.matcher(link);
    }

    @Override
    public Result getNewResult(Matcher matcher, String link) {
        return new GitHubResult(matcher.group("username"), matcher.group("repository"));
    }

}
