package com.sidroded.englishbotmongodb.database.controller;

import com.sidroded.englishbotmongodb.database.model.User;
import com.sidroded.englishbotmongodb.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveData() {
        userRepository.save(new User("chat", "daniil"));
        userRepository.save(new User("chat", "asdsa"));
    }

    public List<User> getData() {
        return userRepository.findAll();
    }
}
