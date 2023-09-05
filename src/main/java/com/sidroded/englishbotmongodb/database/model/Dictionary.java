package com.sidroded.englishbotmongodb.database.model;

import java.util.List;

public class Dictionary {
    private String chatId;
    private String dictionaryName;
    private List<CombinationWord> combinationWordList;

    public Dictionary(String chatId, String dictionaryName) {
        super();
        this.chatId = chatId;
        this.dictionaryName = dictionaryName;
    }

    public Dictionary(String chatId, String dictionaryName, List<CombinationWord> combinationWordList) {
        super();
        this.chatId = chatId;
        this.dictionaryName = dictionaryName;
        this.combinationWordList = combinationWordList;
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

    public List<CombinationWord> getTranslationList() {
        return combinationWordList;
    }

    public void setTranslationList(List<CombinationWord> combinationWordList) {
        this.combinationWordList = combinationWordList;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "chatId='" + chatId + '\'' +
                ", dictionaryName='" + dictionaryName + '\'' +
                '}';
    }
}
