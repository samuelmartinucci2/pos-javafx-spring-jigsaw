package com.samuel.service.auth;

import com.samuel.service.util.LoginRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

public class AdminPrincipal implements UserDetails {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(LoginRoles.ADMIN));
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(PASSWORD);
    }

    @Override
    public String getUsername() {
        return USERNAME;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
