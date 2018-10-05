package com.JediMasterSpring.JediBot.bot;


import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.generics.WebhookBot;

import com.github.unafraid.telegrambot.bots.DefaultTelegramBot;

public abstract class TelegramWebHookBot extends DefaultTelegramBot implements WebhookBot {

    public TelegramWebHookBot(String token, String username){
        super(token, username);
    }

    @Override
    public final BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update != null) {
            onUpdateReceived(update);
        }
        return null;
    }


}
