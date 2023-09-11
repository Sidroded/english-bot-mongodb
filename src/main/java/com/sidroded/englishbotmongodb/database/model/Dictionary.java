package com.sidroded.englishbotmongodb.database.model;

import java.util.List;

public class Dictionary {
    private String chatId;
    private String dictionaryName;
    private List<LinguisticUnit> linguisticUnitList;

    public Dictionary() {
    }

    public Dictionary(String chatId, String dictionaryName) {
        super();
        this.chatId = chatId;
        this.dictionaryName = dictionaryName;
    }

    public Dictionary(String chatId, String dictionaryName, List<LinguisticUnit> linguisticUnitList) {
        super();
        this.chatId = chatId;
        this.dictionaryName = dictionaryName;
        this.linguisticUnitList = linguisticUnitList;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public List<LinguisticUnit> getLinguisticUnitList() {
        return linguisticUnitList;
    }

    public void setTranslationList(List<LinguisticUnit> linguisticUnitList) {
        this.linguisticUnitList = linguisticUnitList;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "chatId='" + chatId + '\'' +
                ", dictionaryName='" + dictionaryName + '\'' +
                '}';
    }
}
