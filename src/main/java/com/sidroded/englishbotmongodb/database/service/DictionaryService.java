package com.sidroded.englishbotmongodb.database.service;

import com.sidroded.englishbotmongodb.database.model.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DictionaryService {
    private final UserService userService;

    @Autowired
    public DictionaryService(UserService userService) {
        this.userService = userService;
    }

    public Dictionary getDictionaryByDictionaryNameAndChatId(String chatId, String dictionaryName) {
        List<Dictionary> dictionaryList = userService.getDictionaryListById(chatId);
        Dictionary dictionary = null;

        for (Dictionary dictionaryItem : dictionaryList) {
            if (dictionaryItem.getDictionaryName().equals(dictionaryName)) {
                dictionary = dictionaryItem;
            }
        }

        return dictionary;
    }
}
