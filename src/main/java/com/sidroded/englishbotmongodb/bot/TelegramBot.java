package com.sidroded.englishbotmongodb.bot;

import com.sidroded.englishbotmongodb.bot.command.ChooseDictionaryCommand;
import com.sidroded.englishbotmongodb.bot.command.StartCommand;
import com.sidroded.englishbotmongodb.bot.config.BotConfig;
import com.sidroded.englishbotmongodb.bot.constant.ButtonText;
import com.sidroded.englishbotmongodb.database.model.Dictionary;
import com.sidroded.englishbotmongodb.database.service.DictionaryService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    public TelegramBot(BotConfig botConfig, StartCommand startCommand, ChooseDictionaryCommand chooseDictionaryCommand, DictionaryService dictionaryService) {
        this.botConfig = botConfig;
        this.startCommand = startCommand;
        this.chooseDictionaryCommand = chooseDictionaryCommand;
        this.dictionaryService = dictionaryService;
    }

    private final BotConfig botConfig;
    private final StartCommand startCommand;
    private final DictionaryService dictionaryService;
    private final ChooseDictionaryCommand chooseDictionaryCommand;

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userName = update.getMessage().getChat().getFirstName();
            String text = update.getMessage().getText();

            switch (text) {
                case ButtonText.START_BUTTON -> startCommandReceived(chatId, userName);
                case ButtonText.CHOOSE_DICTIONARY_BUTTON -> chooseDictionaryCommandReceived(chatId);
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());

            checkDictionaryCallbackData(callBackData, chatId);
        }
    }

    private void startCommandReceived(String chatId, String userName) {
        try {
            execute(startCommand.getStartMessage(chatId, userName));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void chooseDictionaryCommandReceived(String chatId) {
        try {
            execute(chooseDictionaryCommand.chooseDictionaryGetMessage(chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkDictionaryCallbackData(String callBackData, String chatId) {
        Dictionary dictionary = dictionaryService.getDictionaryByDictionaryNameAndChatId(chatId, callBackData);

        if (dictionary != null) {
            try {
                execute(chooseDictionaryCommand.showDictionaryGetMessage(chatId, dictionary));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
