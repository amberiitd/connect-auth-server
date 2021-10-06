package com.namber.connect.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDetailsRepositoy {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User fetchByUserName(String username){
        if (username == null){
            return null;
        }
        List<User> users = new ArrayList<>();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        users.add(new User("amber", passwordEncoder.encode("azu"), authorities));
        users.add(new User("user", passwordEncoder.encode("password"), authorities));
        users = users.stream().filter(user -> username.equals(user.getUsername())).collect(Collectors.toList());
        if (users.size() > 0){
            return users.get(0);
        }else{
            return null;
        }
    }
}
