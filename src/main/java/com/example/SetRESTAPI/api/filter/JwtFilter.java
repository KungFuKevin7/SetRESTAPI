package com.example.SetRESTAPI.api.filter;

import com.example.SetRESTAPI.api.service.JwtService;
import com.example.SetRESTAPI.api.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Token Format: "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNci4gTmljZSBHdXkiLCJpYXQiOjE3MzYzNDQxOTksImV4cCI6MTczNjM0NTA5OX0.8vHFmVOkErbM13-0Dp2FCHCskkDCU6YW6MwelmFIrc8"

        String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;

        //Check if format of token is valid
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7); // Start reading from the 7th character (Skip "Bearer ")
            username = jwtService.extractUserName(jwtToken);
        }

        //If username is not empty, get userdetails from database (loadUserByUsername)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(username);

            //Verify the user input with the credentials stored in the database
            if(jwtService.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
                //Principals
                //Credentials
                //Authorities
            }
        }
        //Continue the filter, handle next filter
        filterChain.doFilter(request, response);

    }
}

