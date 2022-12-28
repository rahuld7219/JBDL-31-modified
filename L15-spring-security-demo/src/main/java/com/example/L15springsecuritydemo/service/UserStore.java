package com.example.L15springsecuritydemo.service;

import com.example.L15springsecuritydemo.dbmodel.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * service for inMemory user store
 */
@Service
public class UserStore implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, MyUser> userMap = new HashMap<>(); // Using HashMap to store user info, i.e., inMemory store

    /**
     * It is like sign up, i.e., storing users
     */
    @PostConstruct
    public void init(){
        userMap.put("shashi",new MyUser("shashi",passwordEncoder.encode("123"),"user"));
        // storing password after encoding using the password encoder used, the same password encoder must be
        // specified in authenticaton configuration in configure(AuthenticationManagerBuilder auth)
        userMap.put("ravi",new MyUser("ravi",passwordEncoder.encode("123"),"admin"));
    }

    /**
     * This method runs on /login, as it is specified in configure(AuthenticationManagerBuilder auth)
     * while configuring spring security
     *
     * This method loads the user details from the user store (inMemory, database, etc.) and
     * provides/return the user details for authentication (to some spring security method)
     *
     * This method automatically receives the username entered by the client while login,
     * we override this method to write logic for from where we want to
     * read/load user info(inMemory, Redis, MySQL, from other API using RestTemplate, etc.) and
     * it returns that info to spring security for authentication
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userMap.get(username);
        if(userDetails==null){
            throw new UsernameNotFoundException("No user with username "+username);
        }
        return userDetails;
    }
}
