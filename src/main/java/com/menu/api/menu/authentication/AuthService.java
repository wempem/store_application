package com.menu.api.menu.authentication;

import com.menu.api.menu.user.CustomPrincipal;
import com.menu.api.menu.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        return principal.getUser().getIs_admin();
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        return principal.getUser();
    }
}
