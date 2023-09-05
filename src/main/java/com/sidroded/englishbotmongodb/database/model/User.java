package com.sidroded.englishbotmongodb.database.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("users")
public class User {
    private String chatId;
    private String name;
    private List<Dictionary> dictionaryList;

    public User() {
    }

    public User(String chatId, String name) {
        super();
        this.chatId = chatId;
        this.name = name;
    }

    public User(String chatId, String name, List<Dictionary> dictionaryList) {
        super();
        this.chatId = chatId;
        this.name = name;
        this.dictionaryList = dictionaryList;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dictionary> getDictionaryList() {
        return dictionaryList;
    }

    public void setDictionaryList(List<Dictionary> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return chatId.equals(user.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }
}
