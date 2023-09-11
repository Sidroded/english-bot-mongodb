package com.sidroded.englishbotmongodb.bot.command;

import com.sidroded.englishbotmongodb.bot.constant.ButtonId;
import com.sidroded.englishbotmongodb.bot.constant.ButtonText;
import com.sidroded.englishbotmongodb.bot.constant.MessageText;
import com.sidroded.englishbotmongodb.database.model.Dictionary;
import com.sidroded.englishbotmongodb.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChooseDictionaryCommand {

    private final UserService userService;

    @Autowired
    public ChooseDictionaryCommand(UserService userService) {
        this.userService = userService;
    }

    public SendMessage chooseDictionaryGetMessage(String chatId) {
        List<Dictionary> dictionaryList = userService.getDictionaryListById(chatId);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        if (dictionaryList == null) {
            message.setText(MessageText.CHOOSE_DICTIONARY_EMPTY_DICTIONARY_LIST);
            message.setReplyMarkup(getKeyboardForEmptyDictionaryList());
        } else {
            message.setText(MessageText.CHOOSE_DICTIONARY_FILLED_DICTIONARY_LIST);
            message.setReplyMarkup(getKeyboardForFilledDictionaryList(dictionaryList));
        }

        return message;
    }

    public SendMessage showDictionaryGetMessage(String chatId, Dictionary dictionary) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(dictionary.getDictionaryName());

        return message;
    }

    private InlineKeyboardMarkup getKeyboardForEmptyDictionaryList() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(ButtonText.ADD_DICTIONARY_BUTTON);
        button1.setCallbackData(ButtonId.ADD_DICTIONARY_BUTTON);

        row.add(button1);
        rows.add(row);

        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }

    private InlineKeyboardMarkup getKeyboardForFilledDictionaryList(List<Dictionary> dictionaryList) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (Dictionary dictionary : dictionaryList) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(dictionary.getDictionaryName());
            button.setCallbackData(dictionary.getDictionaryName());

            row.add(button);
            rows.add(row);
        }

        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }


}
