package com.sidroded.englishbotmongodb.database.controller;

import com.sidroded.englishbotmongodb.database.model.User;
import com.sidroded.englishbotmongodb.database.repository.UserRepository;
import com.sidroded.englishbotmongodb.database.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserController {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
        log.debug("User {}, added.", user.getName());
    }

    public List<User> getData() {
        return userRepository.findAll();
    }

    public User getUserByChatId(String chatId) {
        return userRepository.findUserByChatId(chatId);
    }
}
