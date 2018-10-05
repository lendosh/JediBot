package com.JediMasterSpring.JediBot.bot.handlers.impl;

import org.springframework.stereotype.Service;
import com.github.unafraid.telegrambot.handlers.CommandHandlers;
import com.github.unafraid.telegrambot.handlers.ICancelHandler;
import com.github.unafraid.telegrambot.handlers.ICommandHandler;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

@Service
public class CancelHandler implements ICommandHandler {

    @Override
    public String getCommand() {
        return "/cancel";
    }

    @Override
    public String getUsage() {
        return "/cancel";
    }

    @Override
    public String getDescription() {
        return "Cancels current action";
    }

    @Override
    public void onCommandMessage(AbsSender bot, Update update, Message message, List<String> args) throws TelegramApiException {
        for(ICancelHandler handler : CommandHandlers.getInstance().getHandlers(ICancelHandler.class, message.getFrom())){
            handler.onCancel(bot, update, message);
        }
    }
}
