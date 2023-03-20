package ru.tinkoff.edu.java.linkparser.parser;

import ru.tinkoff.edu.java.linkparser.result.Result;

import java.util.regex.Matcher;

public interface LinkParserChain {
    Matcher getNewMatcher(String link);
    Result getNewResult(Matcher matcher, String link);
    Result parseLink(String link);
    void setNextChain(LinkParserChain nextChain);
}
