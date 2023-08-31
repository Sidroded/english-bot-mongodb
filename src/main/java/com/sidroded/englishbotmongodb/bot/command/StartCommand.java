package com.sidroded.englishbotmongodb.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartCommand {

    public SendMessage getStartMessage(String chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(userName);

        return message;
    }
}
