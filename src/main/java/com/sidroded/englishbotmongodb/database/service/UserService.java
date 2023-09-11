package com.sidroded.englishbotmongodb.database.service;

import com.sidroded.englishbotmongodb.database.controller.UserController;
import com.sidroded.englishbotmongodb.database.model.Dictionary;
import com.sidroded.englishbotmongodb.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserController userController;

    @Autowired
    public UserService(UserController userController) {
        this.userController = userController;
    }

    public boolean isNewUser(String chatId) {
        List<User> users = userController.getData();

        for (User user : users) {
            if (user.getChatId().equals(chatId)) {
                return false;
            }
        }
        return true;
    }

    public void addUser(User user) {
        userController.saveUser(user);
    }

    public User createNewUser(String chatId, String name) {
        return new User(chatId, name);
    }

    public List<Dictionary> getDictionaryListById(String chatId) {
        User user = userController.getUserByChatId(chatId);
        return user.getDictionaryList();
    }

    public User createTestUser(String chatId, String name) {
        User user = new User(chatId, name);
        List<Dictionary> dictionaryList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Dictionary dictionary = new Dictionary(chatId, "Dict" + i);
            dictionaryList.add(dictionary);
        }

        user.setDictionaryList(dictionaryList);

        return user;
    }
}
