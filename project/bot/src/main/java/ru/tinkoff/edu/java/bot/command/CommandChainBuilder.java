package ru.tinkoff.edu.java.bot.command;

import jdk.dynalink.linker.LinkerServices;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.service.LinkSearcher;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

import java.util.List;

public class CommandChainBuilder {

    public static CommandChain build(String scrapperUrl ){
        MessageProcessor messageProcessor = new MessageProcessor();
        ScrapperClient scrapperClient = new ScrapperClient(scrapperUrl);
        LinkSearcher linkSearcher = new LinkSearcher(scrapperClient);

        HelpCommand helpCommand = new HelpCommand(messageProcessor);
        StartCommand startCommand = new StartCommand(messageProcessor);
        TrackCommand trackCommand = new TrackCommand(messageProcessor);
        UntrackCommand untrackCommand = new UntrackCommand(messageProcessor);
        ListCommand listCommand = new ListCommand(messageProcessor, linkSearcher);
        InvalidCommand invalidCommand = new InvalidCommand(messageProcessor);

        List<CommandChain> coomandList = List.of(startCommand, trackCommand, untrackCommand, listCommand, invalidCommand);
        CommandChain nowChain = helpCommand;

        for (CommandChain chain : coomandList){
            chain.setNextCommandChain(nowChain);
            nowChain = chain;
        }
        return nowChain;
    }

    public static CommandChain build(CommandChain first, CommandChain... chains){
        CommandChain nowChain = first;
        for (CommandChain chain : chains){
            chain.setNextCommandChain(nowChain);
            nowChain = chain;
        }
        return nowChain;
    }
}
