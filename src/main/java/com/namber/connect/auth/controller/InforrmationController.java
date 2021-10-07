package com.namber.connect.auth.controller;

import com.namber.connect.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InforrmationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/roles")
    public List<String> getRoles(){
        return userDetailsService.getAuthenticatedUserRoles();
    }

    @PostMapping("/user-logout")
    public void logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
