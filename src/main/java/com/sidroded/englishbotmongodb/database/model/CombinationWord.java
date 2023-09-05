package com.sidroded.englishbotmongodb.database.model;

import java.util.Objects;

public class CombinationWord {
    private String word;
    private String translation;

    public CombinationWord(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CombinationWord that = (CombinationWord) o;
        return word.equals(that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
