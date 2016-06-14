package ru.proshik.jalmew.word.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.proshik.jalmew.word.dto.AuthObject;
import ru.proshik.jalmew.word.dto.TokenTransfer;
import ru.proshik.jalmew.word.util.TokenUtils;

import java.util.Arrays;

/**
 * Created by proshik on 13.06.16.
 */
@Controller
@RequestMapping("security")
public class AuthController {

//    @Autowired
//    @Qualifier("userDetailsService")
//    private UserDetailsService userDetailsService;

//    @Autowired
////    @Qualifier("authenticationManager")
//    private AuthenticationManager authManager;


    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    public TokenTransfer signIn(@RequestBody AuthObject param) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword());
//        Authentication authentication = this.authManager.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
         * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(param.getUsername());
        User det = new User("test", "test", Arrays.asList(new SimpleGrantedAuthority("admin")));
        return new TokenTransfer(TokenUtils.createToken(det));
    }

    @RequestMapping(value="registration", method = RequestMethod.POST)
    public TokenTransfer signUp(@RequestBody  AuthObject param){
        User det = new User("test", "test", Arrays.asList(new SimpleGrantedAuthority("admin")));
        return new TokenTransfer(TokenUtils.createToken(det));
    }


}
