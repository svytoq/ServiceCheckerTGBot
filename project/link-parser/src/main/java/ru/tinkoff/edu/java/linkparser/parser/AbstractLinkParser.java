package ru.tinkoff.edu.java.linkparser.parser;

import ru.tinkoff.edu.java.linkparser.result.Result;

import java.util.regex.Matcher;

public abstract class AbstractLinkParser implements LinkParserChain {
    protected LinkParserChain nextChain;


    abstract Matcher getNewMatcher(String link);
    abstract Result getNewResult(Matcher matcher, String link);

    @Override
    public Result parseLink(String link) {
        Matcher matcher = getNewMatcher(link);
        if(matcher.matches()){
            return getNewResult(matcher, link);
        }
        if(nextChain != null){
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
