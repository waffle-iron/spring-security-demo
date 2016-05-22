package ru.proshik.jalmew.word.model.ydict;

import java.util.Optional;

/**
 * Created by proshik on 22.05.16.
 */
public class YTranslateWord {

    private Head head;

    private Definition[] def;

    public YTranslateWord() {
    }

    public Head getHead() {
        return head;
    }

    public Definition[] getDef() {
        return def;
    }
}
