package ru.tinkoff.edu.java.linkparser.parser;

import ru.tinkoff.edu.java.linkparser.result.Result;

public interface LinkParserChain {
    Result parseLink(String link);
    void setNextChain(LinkParserChain nextChain);
}
