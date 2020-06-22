package com.freitas.lucas.carregistration.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationService {

    private AuthenticationService(){
    }

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
}
