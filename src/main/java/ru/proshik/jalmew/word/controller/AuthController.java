package ru.proshik.jalmew.word.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.proshik.jalmew.word.configuration.TokenValidationUtils;
import ru.proshik.jalmew.word.dto.TokenTransfer;
import ru.proshik.jalmew.word.model.Users;
import ru.proshik.jalmew.word.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by proshik on 13.06.16.
 */
@Controller
@RequestMapping("security")
public class AuthController {

    @Autowired
    private TokenValidationUtils tokenValidationUtils;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    public ResponseEntity signIn() {
        Optional<Users> user = userRepository.findByUsername("user");
        Users users = user.get();
        User user1 = new User(users.getUsername(), users.getPassword(), Collections.emptyList());
        return ResponseEntity.ok(new TokenTransfer(tokenValidationUtils.createToken(user1).getToken()));
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public void signUp() {
    }


}
