package ru.proshik.jalmew.word.service.exception;

/**
 * Created by proshik on 22.05.16.
 */
public class UserWordAlreadyExistException extends Exception {

    public UserWordAlreadyExistException(String message) {
        super(message);
    }
}
