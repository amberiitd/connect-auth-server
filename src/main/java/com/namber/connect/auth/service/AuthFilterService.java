package com.namber.connect.auth.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthFilterService extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        List<GrantedAuthority> authorities = new ArrayList(){{
//            add(new SimpleGrantedAuthority("USER"));
//        }};
//
//        User user = new User("amber", "azu", authorities);
//
//        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authToken);
        response.setHeader("Access-control-Allow-Origin", "*"); //for cors policy issue with options method
//        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, x-auth-token, Content-Type, api_key, Authorization");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK); // for cors policy issue with options method
//        } else {
//            filterChain.doFilter(request, response);
//        }
        filterChain.doFilter(request, response);


    }
}
