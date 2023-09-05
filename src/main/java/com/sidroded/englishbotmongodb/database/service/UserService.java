package com.sidroded.englishbotmongodb.database.service;

import com.sidroded.englishbotmongodb.database.controller.UserController;
import com.sidroded.englishbotmongodb.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserController userController;

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
}
