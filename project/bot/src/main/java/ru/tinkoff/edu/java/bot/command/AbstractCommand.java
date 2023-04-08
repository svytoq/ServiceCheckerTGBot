package ru.tinkoff.edu.java.bot.command;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.tinkoff.edu.java.bot.enums.CommandName;
import ru.tinkoff.edu.java.bot.service.MessageProcessor;

@Getter
@Setter
public abstract class AbstractCommand implements CommandChain{

    protected CommandName commandName;
    protected MessageProcessor messageProcessor;
    protected CommandChain nextCommandChain;
    @Override
    public BotCommand toApiCommand() {
        return new BotCommand(commandName.getCommandName(), commandName.getCommandDescription());
    }

    @Override
    abstract public SendMessage execute(Update update);

    @Override
    public SendMessage process(CommandName commandName, Update update){
        if (this.commandName.equals(commandName)){
            return execute(update);
        }else if(this.nextCommandChain != null){
            return nextCommandChain.process(commandName, update);
        }else {
            return null;
        }
    }
}
