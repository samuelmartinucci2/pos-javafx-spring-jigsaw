package com.samuel.service.impl;

import com.samuel.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(final String username, final String password) {
        Authentication authtoken = new UsernamePasswordAuthenticationToken(
                username, password);
        try {
            authtoken = authenticationManager.authenticate(authtoken);
            SecurityContextHolder.getContext().setAuthentication(authtoken);
            return true;
        } catch (final AuthenticationException e) {
            return false;
        }
    }

    @Override
    public Set<String> loggedUserRoles() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}
