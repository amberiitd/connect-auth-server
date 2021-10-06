package com.namber.connect.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class DefaultController {
    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(Model model){
        User principal =null;
        try {
            principal =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        }catch (Exception e){
            log.error(e.getMessage());
        }

        String name = "User";
        if(principal !=null) {
            name = principal.getUsername();
            log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        }
        model.addAttribute("name", name);

        return "welcome";
    }

    @GetMapping("/login-page")
    public String login(@RequestParam( required = false, value = "error") String error, @RequestParam(required = false, value = "logout") String logout){
        return "login";
    }
}
