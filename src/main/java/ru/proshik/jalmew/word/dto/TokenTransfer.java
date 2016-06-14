package ru.proshik.jalmew.word.dto;

/**
 * Created by proshik on 13.06.16.
 */
public class TokenTransfer {

    private String token;

    public TokenTransfer() {
    }

    public TokenTransfer(String token)
    {
        this.token = token;
    }


    public String getToken()
    {
        return this.token;
    }

}
