package ru.proshik.jalmew.word.model.ydict;

/**
 * Created by proshik on 22.05.16.
 */
public class Example {

    private String text;

    private ExampleTransfer[] tr;

    public Example() {
    }

    public String getText() {
        return text;
    }

    public ExampleTransfer[] getTr() {
        return tr;
    }
}
