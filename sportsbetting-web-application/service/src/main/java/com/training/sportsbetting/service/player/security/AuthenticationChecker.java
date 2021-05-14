package com.training.sportsbetting.service.player.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationChecker {

    public boolean isAuthenticated() {
        return hasAuthentication() && isValid() && !isAnonymous();
    }

    private boolean hasAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    private boolean isValid() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    private static boolean isAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
    }

}
