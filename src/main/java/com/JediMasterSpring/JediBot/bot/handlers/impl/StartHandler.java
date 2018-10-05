package com.JediMasterSpring.JediBot.bot.handlers.impl;

import com.JediMasterSpring.JediBot.services.UserService;
import com.github.unafraid.telegrambot.handlers.ICommandHandler;
import com.github.unafraid.telegrambot.util.BotUtil;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class StartHandler implements ICommandHandler {
    private UserService userService;

    private final AtomicBoolean createdAdmin = new AtomicBoolean();

    @Inject
    public StartHandler(UserService userService){
        this.userService = userService;
    }
    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public String getUsage() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "Welcome to our galaxy,Youngling";
    }

    @Override
    public void onCommandMessage(AbsSender bot, Update update, Message message, List<String> args) throws TelegramApiException {
        if (!createdAdmin.get() && userService.findAll().isEmpty()) {

            // В этом кейсе, когда не создано ниодного юзера, первый кто напишет будет боту и будет помечен как админ ¯\_(ツ)_/¯

            if (createdAdmin.compareAndSet(false, true)) {
                userService.create(message.getFrom().getId(), message.getFrom().getUserName(), 10);
                BotUtil.sendMessage(bot, message, "Hello Stranger, i am " + bot.getMe().getUserName() + ", if you want to come to our side type /start", true, false, null);

            } else {
                // Когда уже есть админ
                createdAdmin.set(true);
                BotUtil.sendMessage(bot, message, "Hello Stranger, i am " + bot.getMe().getUserName() + ", if you want to come to our side type /start", true, false, null);
            }
        }
    }
}