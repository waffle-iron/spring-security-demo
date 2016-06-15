package ru.proshik.jalmew.word.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;

/**
 * Created by proshik on 15.06.16.
 */
public class TokenAuthenticationProvider implements AuthenticationProvider, InitializingBean {

    private static final Log LOG = LogFactory
            .getLog(TokenAuthenticationProvider.class);

    @Autowired
    private TokenProvider tokenProvider;

    private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> preAuthenticatedUserDetailsService = null;
    private UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();
    private boolean throwExceptionWhenTokenRejected = false;

    public TokenAuthenticationProvider() {
    }

    public TokenAuthenticationProvider(AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> preAuthenticatedUserDetailsService) {
        this.preAuthenticatedUserDetailsService = preAuthenticatedUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("PreAuthenticated authentication request: " + authentication);
        }

        if (authentication.getPrincipal() == null) {
            LOG.debug("No pre-authenticated principal found in request.");

            if (throwExceptionWhenTokenRejected) {
                throw new BadCredentialsException(
                        "No pre-authenticated principal found in request.");
            }
            return null;
        }

        if (authentication.getCredentials() == null) {
            LOG.debug("No pre-authenticated credentials found in request.");

            if (throwExceptionWhenTokenRejected) {
                throw new BadCredentialsException(
                        "No pre-authenticated credentials found in request.");
            }
            return null;
        }

        UserDetails ud = preAuthenticatedUserDetailsService
                .loadUserDetails((PreAuthenticatedAuthenticationToken) authentication);

        Token token = tokenProvider.createToken(ud);

        System.out.println(token);

        userDetailsChecker.check(ud);

        if (tokenProvider.validateToken(token.getToken(), ud)){
            PreAuthenticatedAuthenticationToken result = new PreAuthenticatedAuthenticationToken(
                    ud, authentication.getCredentials(), ud.getAuthorities());
            result.setDetails(authentication.getDetails());

            return result;
        }else {
            throw new BadCredentialsException("Token did not pass validation");
        }

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }

    public void setPreAuthenticatedUserDetailsService(
            AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> uds) {
        this.preAuthenticatedUserDetailsService = uds;
    }

    /**
     * Check whether all required properties have been set.
     */
    public void afterPropertiesSet() {
        Assert.notNull(preAuthenticatedUserDetailsService,
                "An AuthenticationUserDetailsService must be set");
    }
}