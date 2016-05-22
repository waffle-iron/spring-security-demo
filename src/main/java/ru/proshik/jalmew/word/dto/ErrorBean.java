package ru.proshik.jalmew.word.dto;

/**
 * Created by proshik on 23.05.16.
 */
public class ErrorBean {

    private String text;

    public ErrorBean() {
    }

    public ErrorBean(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
