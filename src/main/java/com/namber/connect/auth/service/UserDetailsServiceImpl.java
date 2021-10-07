package com.namber.connect.auth.service;

import com.namber.connect.auth.dao.UserDetailsRepositoy;
import com.namber.connect.auth.model.OAuthUserDetails;
import com.namber.connect.auth.model.request.UserDTO;
import com.namber.connect.auth.utils.constant.Authority;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsRepositoy userDetailsRepositoy;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OAuthUserDetails userDetails = userDetailsRepositoy.findByUsername(username);

        if (userDetails == null || userDetails.getUsername() == null ){
            throw new UsernameNotFoundException("user does not exit");
        }
        return new User(userDetails.getUsername(), userDetails.getPassword(), getParsedAuthorities(userDetails.getAuthorities()));
    }

    public void register(UserDTO user) {
        if(userDetailsRepositoy.findByUsername(user.getUsername())==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDetailsRepositoy.save(mapper.map(user, OAuthUserDetails.class));
        }
    }

    public void update(String username, String authorities) {
        OAuthUserDetails userDetails = userDetailsRepositoy.findByUsername(username);

        if(userDetails!=null){
            userDetails.setAuthorities(userDetails.getAuthorities()+ Authority.DELIM_REGEX +
                    authorities.replaceAll("\\s", ""));
            userDetailsRepositoy.save(userDetails);
        }
    }

    public void removeUser(String username) {
        OAuthUserDetails user = userDetailsRepositoy.findByUsername(username);
        if (user !=null){
            userDetailsRepositoy.deleteById(user.getUserId());
        }
    }

    public List<String> getAuthenticatedUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = new ArrayList<>();
        if (authentication != null ){
            authentication.getAuthorities().forEach( auth -> {
                roles.add(auth.getAuthority());
            });
        }

        return roles;
    }

    private Collection<? extends GrantedAuthority> getParsedAuthorities(String authorities) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (String auth: authorities.split(",")){
            grantedAuthorityList.add(new SimpleGrantedAuthority(auth));
        }

        return grantedAuthorityList;
    }

}
