package com.sidroded.englishbotmongodb.bot;

import com.sidroded.englishbotmongodb.bot.command.StartCommand;
import com.sidroded.englishbotmongodb.bot.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    private final BotConfig botConfig;

    @Autowired
    StartCommand startCommand;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }


    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String userName = update.getMessage().getChat().getFirstName();
        String text = update.getMessage().getText();

        switch (text) {
            case "/start" -> startCommandReceived(chatId, userName);
        }
    }

    private void startCommandReceived(String chatId, String userName) {

        try {
            execute(startCommand.getStartMessage(chatId, userName));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
