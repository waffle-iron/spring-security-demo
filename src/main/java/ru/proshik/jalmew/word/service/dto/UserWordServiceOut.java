package ru.proshik.jalmew.word.service.dto;

import ru.proshik.jalmew.word.model.LearningState;

/**
 * Created by proshik on 22.05.16.
 */
public class UserWordServiceOut {

    private String word;

    private LearningState learningState;

    public UserWordServiceOut() {
    }

    public UserWordServiceOut(String word, LearningState learningState) {
        this.word = word;
        this.learningState = learningState;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public LearningState getLearningState() {
        return learningState;
    }

    public void setLearningState(LearningState learningState) {
        this.learningState = learningState;
    }
}
