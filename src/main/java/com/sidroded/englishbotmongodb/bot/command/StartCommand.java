package com.sidroded.englishbotmongodb.bot.command;

import com.sidroded.englishbotmongodb.bot.constant.ButtonText;
import com.sidroded.englishbotmongodb.bot.constant.MessageText;
import com.sidroded.englishbotmongodb.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;


@Component
public class StartCommand {

    private final UserService userService;

    @Autowired
    public StartCommand(UserService userService) {
        this.userService = userService;
    }

    public SendMessage getStartMessage(String chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(getStartText(chatId, userName));
        message.setReplyMarkup(getInlineKeyboardMarkup());

        return message;
    }

    private String getStartText(String chatId, String username) {
        if (userService.isNewUser(chatId)) {
            userService.addUser(userService.createNewUser(chatId, username));

            return String.format(MessageText.START_COMMAND_RECEIVED_NEW_USER, username);
        } else {
            return String.format(MessageText.START_COMMAND_RECEIVED_OLD_USER, username);
        }
    }

    private ReplyKeyboardMarkup getInlineKeyboardMarkup() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardButton> row = new ArrayList<>();
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardButton button1 = new KeyboardButton();
        button1.setText(ButtonText.ADD_WORD_BUTTON);

        KeyboardButton button2 = new KeyboardButton();
        button2.setText(ButtonText.CHOOSE_DICTIONARY_BUTTON);

        row.add(button1);
        row.add(button2);

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(row);
        rows.add(keyboardRow);

        keyboardMarkup.setKeyboard(rows);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup;
    }
}
