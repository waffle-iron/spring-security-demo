package ru.proshik.jalmew.word.model.ydict;

/**
 * Created by proshik on 22.05.16.
 */
public class Definition {

    private String text;

    private String pos;

    private String ts;

    private Transfer[] tr;

    public Definition() {
    }

    public String getText() {
        return text;
    }

    public String getPos() {
        return pos;
    }

    public String getTs() {
        return ts;
    }

    public Transfer[] getTr() {
        return tr;
    }
}
