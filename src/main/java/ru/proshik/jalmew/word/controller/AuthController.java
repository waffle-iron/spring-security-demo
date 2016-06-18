package ru.proshik.jalmew.word.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.proshik.jalmew.word.dto.TokenTransfer;

/**
 * Created by proshik on 13.06.16.
 */
@Controller
@RequestMapping("security")
public class AuthController {

    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    public TokenTransfer signIn() {
        return new TokenTransfer("test");
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public void signUp() {
    }


}
