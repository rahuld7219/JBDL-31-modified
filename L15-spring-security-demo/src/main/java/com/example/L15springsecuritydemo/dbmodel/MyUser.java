package com.example.L15springsecuritydemo.dbmodel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    public MyUser() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MyUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
    }

    /**
     * above arg constructor not execute as no arg-constructor will be used.
     *
     * This method executes after entity/data loaded from the database.
     * This method sets the authorities that the user have
     */
    @PostLoad
    public void initAuthorities(){
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
    }

    // we don't want to serialize this field (i.e., don't want to store this field in database)
    // so we make it transient
    transient private List<SimpleGrantedAuthority> authorities; // it will be use when calling
                                                                    // hasAuthority(), hasAnyAuthority() in configure(HttpSecurity )

    @Override // methods of UserDetails interface
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // by default, returns false, we changed it to return true

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // by default, returns false, we changed it to return true

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // by default, returns false, we changed it to return true

    @Override
    public boolean isEnabled() {
        return true;
    } // by default, returns false, we changed it to return true
}
