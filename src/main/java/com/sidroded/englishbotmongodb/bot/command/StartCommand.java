package com.sidroded.englishbotmongodb.bot.command;

import com.sidroded.englishbotmongodb.database.controller.UserController;
import com.sidroded.englishbotmongodb.database.model.Dictionary;
import com.sidroded.englishbotmongodb.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
public class StartCommand {

    UserController userController;

    @Autowired
    public StartCommand(UserController userController) {
        this.userController = userController;
    }

    public SendMessage getStartMessage(String chatId, String userName) {
        userController.saveData();
        List<User> userList =  userController.getData();
        String text = "";
        for (User user : userList) {
            text += user.getName() + user.getChatId();
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        return message;
    }
}
