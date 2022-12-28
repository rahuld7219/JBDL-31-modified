package com.example.L14springsecuritydemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuring spring security
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * This method configures Authentication (i.e., configures login process)
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // we can use database, instead of in memory storage for user store
                .withUser("shashi").password("123").authorities("user","admin"); // defining 'shashi' as both (role)
                    // not specifying any user store here,                                 // user and admin,
                    // directly storing username and password in inMemory,                 // i.e., shashi has both user and
                    // also storing password directly as NoOpPasswordEncoder used          // admin authorities

    }

    // We must provide password encoder, otherwise get exception
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance(); // no encoding of password, it will be processed just as plain text as given
    }



//    protected void configure(HttpSecurity http) throws Exception {
////        this.logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
//        http.authorizeRequests((requests) -> {
//            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests
//            .anyRequest())
//            .authenticated(); // This is default configuration,
                                // by default it is giving authorization to all authenticated user for all requests
//        });
//        http.formLogin();
//        http.httpBasic();
//    }


    /**
     *
     * This method configures Authorization
     *
     * All the endpoints specified in this method are secured and others are not secured(i.e. not require authentication)
     *
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {
//        this.logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
        http.authorizeRequests()
                .antMatchers("/admin-api/hello")
                .hasAuthority("admin"); // It is giving authorization for /admin-api/hello API only to admin,
                                            // i.e., whoever have authorities/role of admin can access /admin-api/hello API
        http.formLogin();
        http.httpBasic();
    }



}
