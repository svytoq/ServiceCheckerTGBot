package ru.tinkoff.edu.java.linkparser.parser;

public class LinkParserBuilder {

    public static LinkParserChain build(){
        LinkParserChain commonChain = new GitHubLinkParser();
        commonChain.setNextChain(new StackOverflowLinkParser());
        return commonChain;
    }

    public static LinkParserChain build(LinkParserChain first, LinkParserChain... chains){
        LinkParserChain nowChain = first;
        for (LinkParserChain chain : chains){
            chain.setNextChain(nowChain);
            nowChain = chain;
        }
        return nowChain;
    }
}
