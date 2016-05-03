package ru.proshik.jalmew.word.dto;


import java.util.Optional;

/**
 * Created by proshik on 01.05.16.
 */
public class WordRestIn {

    private Optional<String> word;

    public WordRestIn() {
    }

    public WordRestIn(Optional<String> word) {
        this.word = word;
    }

    public Optional<String> getWord() {
        return word;
    }
}
