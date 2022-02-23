package com.samuel.service;

import java.util.Set;

public interface AuthenticationService {
    boolean login(String username, String password);
    Set<String> loggedUserRoles();
}
