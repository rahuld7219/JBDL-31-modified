package com.example.L15springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuring spring security
 *
 * spring security automatically creates/configure and maintain the session, by default session created in JVM.
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService; // provides implemented user store

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * This method configures Authentication (i.e., configures login process)
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("shashi").password("$2a$10$kWh94q3Mdspgq.uIa32YEurezQdtVS0f5a.1kH0aW6sIeaWU/DSBu").authorities("user")
//                .and()
//                .withUser("ravi").password("$2a$10$A3W3mWPP2fCKXVvRmUpV3uuw3FCwJkkZOxg2iOHn8NpEwhjUB23qa").authorities("admin");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder); // specifies the user store and
                                                                                        // password encoder to use while login
    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder() {return new BCryptPasswordEncoder();}

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); // in constructor, can pass strength also
        System.out.println(bCryptPasswordEncoder.encode("123")); // every time encoder gives different value even
                                                                            // for the same password due to other parameters
                                                                            // included while encoding like time, etc., so that
                                                                            // no one can guess that same hash represent same
                                                                            // password, if he/she can see the database.
                                                                            // that's why we don't use equals() while matching/login
                                                                            // we use matches() of BCrypt for this.
        //$2a$10$A3W3mWPP2fCKXVvRmUpV3uuw3FCwJkkZOxg2iOHn8NpEwhjUB23qa
        //$2a$10$kWh94q3Mdspgq.uIa32YEurezQdtVS0f5a.1kH0aW6sIeaWU/DSBu
    }



//    protected void configure(HttpSecurity http) throws Exception {
////        this.logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
//        http.authorizeRequests((requests) -> {
//            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
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
        http.authorizeRequests().antMatchers("/api/user/hello","/admin/changePassword").hasAnyAuthority("user","admin")
                .antMatchers("/api/admin/hello").hasAuthority("admin"); // can use hasAuthority for specifying a single role
        http.formLogin(); // This is for form based login (i.e., by giving username/email and password) (not OAuth, etc.), we can override the form
        http.httpBasic(); // This is to access our API via Postman, cURL ,etc.
                            // if we don't use this, then we cannot login by providing the
                            // username and password through Authorization field in the header
                            // through postman, cURL,etc.(i.e., from outside the browser),
                            // we always get the login form as the response
                            // and we have to provide username and password in that login form only,
                            // but when we enable httpBasic() then we can login by providing
                            // the username and password through Authorization field
                            // in the header of the http request, we don't get the login form as the reponse, we get logged in

        http.csrf().disable(); // disabling CSRF security. By default, spring provides some default CSRF strategy,
                                // we can override that default strategy and can even disable the CSRF security.
                                // But we should not disable it.
    }



}
