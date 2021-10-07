package com.namber.connect.auth.controller;

import com.namber.connect.auth.model.request.ClientDTO;
import com.namber.connect.auth.model.request.UserDTO;
import com.namber.connect.auth.service.ClientService;
import com.namber.connect.auth.service.UserDetailsServiceImpl;
import com.namber.connect.auth.utils.constant.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/client")
    public void registerClient(@RequestBody ClientDTO client){
        if (hasAuthority() && client.getClientId() != null){
            clientService.register(client);
        }
    }

    @PostMapping("/client/update")
    public void updateClient(@RequestBody ClientDTO client){
        if (hasAuthority() && client.getClientId() != null){
            clientService.update(client);
        }
    }

    @PostMapping("/client/remove")
    public void removeClient(@RequestParam String clientId){
        if (hasAuthority() && clientId != null){
            clientService.removeClient(clientId);
        }
    }

    @PostMapping("/user")
    public void registerUser(@RequestBody UserDTO user){
        if(user.getUsername() != null) {
            userDetailsService.register(user);
        }
    }

    @PostMapping("/user/update-auth")
    public void updateUser(@RequestParam String username, String authorities){
        if(hasAuthority() && username != null) {
            userDetailsService.update(username, authorities);
        }
    }

    @PostMapping("/user/remove")
    public void removeUser(@RequestParam String username){
        if (hasAuthority() && username != null){
            userDetailsService.removeUser(username);
        }
    }


    private boolean hasAuthority(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AtomicBoolean isAuthorized = new AtomicBoolean(false);
        authentication.getAuthorities().forEach(auth -> {
            isAuthorized.set( isAuthorized.get() || Authority.ADMIN.equals(auth.getAuthority()));
        });

        return isAuthorized.get();
    }
}
