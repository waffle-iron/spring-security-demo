package ru.proshik.jalmew.word.dto;

/**
 * Created by proshik on 13.06.16.
 */
public class AuthObject {

    private String username;
    private String password;

    public AuthObject() {
    }

    public AuthObject(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
