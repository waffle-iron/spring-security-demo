package ru.proshik.jalmew.word.configuration;

/**
 * Created by proshik on 15.06.16.
 */
public class Token {

    private String token;
    private long expires;

    public Token() {
    }

    public Token(String token, long expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public long getExpires() {
        return expires;
    }
}
