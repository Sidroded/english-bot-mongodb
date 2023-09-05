package com.sidroded.englishbotmongodb.bot.command;

import com.sidroded.englishbotmongodb.bot.constant.MessageText;
import com.sidroded.englishbotmongodb.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


@Component
public class StartCommand {

    UserService userService;

    private final MessageText messageText = new MessageText();

    @Autowired
    public StartCommand(UserService userService) {
        this.userService = userService;
    }

    public SendMessage getStartMessage(String chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(getStartText(chatId, userName));

        return message;
    }

    private String getStartText(String chatId, String username) {
        if (userService.isNewUser(chatId)) {
            userService.addUser(userService.createNewUser(chatId, username));

            return String.format(messageText.START_COMMAND_RECEIVED_NEW_USER, username);
        } else {
            return String.format(messageText.START_COMMAND_RECEIVED_OLD_USER, username);
        }
    }
}
