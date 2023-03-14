package ru.tinkoff.edu.java.linkparser.parser;

import ru.tinkoff.edu.java.linkparser.result.Result;
import ru.tinkoff.edu.java.linkparser.result.StackOverflowResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackOverflowLinkParser implements LinkParserChain{

    private LinkParserChain nextChain;

    private static final Pattern ID_PATTERN = Pattern.compile(
            "^(?:https://)?(?:www\\.)?stackoverflow\\.com/questions/(?<id>\\d+)(?:/?|/[a-zA-Z\\-]+/?)$"
    );


    @Override
    public Result parseLink(String link) {
        Matcher matcher = ID_PATTERN.matcher(link);
        if(matcher.matches()){
            return new StackOverflowResult(matcher.group("id"));
        }
        else {
            return nextChain.parseLink(link);
        }
    }

    @Override
    public void setNextChain(LinkParserChain nextChain) {
        this.nextChain = nextChain;
    }
}
