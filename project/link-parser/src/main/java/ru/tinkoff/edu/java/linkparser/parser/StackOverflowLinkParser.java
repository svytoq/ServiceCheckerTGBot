package ru.tinkoff.edu.java.linkparser.parser;

import ru.tinkoff.edu.java.linkparser.result.Result;
import ru.tinkoff.edu.java.linkparser.result.StackOverflowResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackOverflowLinkParser extends AbstractLinkParser{

    private LinkParserChain nextChain;

    private static final Pattern ID_PATTERN = Pattern.compile(
            "^(?:https://)?(?:www\\.)?stackoverflow\\.com/questions/(?<id>\\d+)(?:/?|/[a-zA-Z\\-]+/?)$"
    );

    @Override
    public Matcher getNewMatcher(String link) {
        return ID_PATTERN.matcher(link);
    }

    @Override
    public Result getNewResult(Matcher matcher, String link) {
        return new StackOverflowResult(matcher.group("id"));
    }

}
