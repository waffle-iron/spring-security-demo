package ru.proshik.jalmew.word.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by proshik on 06.05.16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    @DependsOn("dataSource")
    public TokenAuthenticationFilter requestHeaderAuthenticationFilter() throws Exception {
        TokenAuthenticationFilter requestHeaderAuthenticationFilter = new TokenAuthenticationFilter();
        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("X-AUTH-TOKEN");
        requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager());
        requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false);
        return requestHeaderAuthenticationFilter;

//        RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();
//        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("X-AUTH-TOKEN");
//        requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false);
//        return requestHeaderAuthenticationFilter;
    }

    @Bean
    public TokenAuthenticationProvider preAuthenticatedAuthenticationProvider() {
//        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
//        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService()));
//        return preAuthenticatedAuthenticationProvider;

        return new TokenAuthenticationProvider(new UserDetailsTokenServiceWrapper(userDetailsService()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/security/**").permitAll()
//                .antMatchers("/greeting").hasRole("ADMIN")
//                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated()
                .and()
//                .addFilter(requestHeaderAuthenticationFilter());

        .addFilterBefore(requestHeaderAuthenticationFilter(), BasicAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .and()
//                .authenticationProvider(new DaoAuthenticationProvider());
//        auth
                .authenticationProvider(preAuthenticatedAuthenticationProvider());
//                .inMemoryAuthentication()
//                .withUser("user1").password("secret1").roles("USER")
//                .and()
//                .withUser("user2").password("secret2").roles("ADMIN");
    }


}
