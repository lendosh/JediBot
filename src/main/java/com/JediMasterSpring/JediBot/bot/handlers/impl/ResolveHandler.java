package com.JediMasterSpring.JediBot.bot.handlers.impl;

import com.JediMasterSpring.JediBot.services.UserService;
import com.github.unafraid.telegrambot.handlers.IAccessLevelHandler;
import com.github.unafraid.telegrambot.handlers.ICommandHandler;
import com.github.unafraid.telegrambot.util.BotUtil;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.inject.Inject;
import java.net.InetAddress;
import java.util.List;

public class ResolveHandler implements ICommandHandler, IAccessLevelHandler {
    private UserService userService;

    @Inject
    public ResolveHandler(UserService userService){ this.userService = userService; }

    @Override
    public int getRequiredAccessLevel() {
        return 1;
    }

    @Override
    public String getCategory() {
        return "Utilities";
    }

    @Override
    public boolean validate(User from) {
        return userService.validate(from.getId(), getRequiredAccessLevel());
    }

    @Override
    public String getCommand() {
        return "/resolve";
    }

    @Override
    public String getUsage() {
        return "/resolve <host>";
    }

    @Override
    public String getDescription() {
        return "Resolved hostname to ip address";
    }

    @Override
    public void onCommandMessage(AbsSender bot, Update update, Message message, List<String> args) throws TelegramApiException {
        if(args.isEmpty()){
            BotUtil.sendUsage(bot, message, this);
                    return;
        }
        final String hostName = args.get(0);
        try{
            final InetAddress address = InetAddress.getByName(hostName);
            BotUtil.sendMessage(bot, message, "*" + hostName + "* = " + address.getHostAddress(), true, true, null);
        } catch (Exception e){
            BotUtil.sendMessage(bot, message, "Failed to resolve: " + hostName + " " + e.getMessage(), true, false, null);
        }

    }
}
