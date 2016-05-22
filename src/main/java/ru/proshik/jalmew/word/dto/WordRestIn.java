package ru.proshik.jalmew.word.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by proshik on 01.05.16.
 */
public class WordRestIn {

    @NotNull
    @Size(min = 1, max = 20)
    private String word;

    public WordRestIn() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
