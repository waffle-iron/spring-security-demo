package ru.proshik.jalmew.word.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * Created by proshik on 18.06.16.
 */

public class UserDetailsTokenServiceWrapper
//        <T extends AuthenticationToken> implements
//        AuthenticationUserDetailsService<T>,
//
        implements InitializingBean {


    private UserDetailsService userDetailsService = null;

    public UserDetailsTokenServiceWrapper(final UserDetailsService userDetailsService) {
        Assert.notNull(userDetailsService, "userDetailsService cannot be null.");
        this.userDetailsService = userDetailsService;
    }

    public UserDetails loadUserDetails(String principal) throws UsernameNotFoundException {
        return this.userDetailsService.loadUserByUsername(principal);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "UserDetailsService must be set");
    }
}
