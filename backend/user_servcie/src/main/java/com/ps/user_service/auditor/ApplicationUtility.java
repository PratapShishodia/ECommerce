package com.ps.user_service.auditor;

import com.ps.user_service.util.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ApplicationUtility {

    public static String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            return "PUBLIC";
        }
        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof CustomUserDetails customUserDetails) {
            username = customUserDetails.getUsername();
        } else {
            username = principal.toString(); // fallback
        }
        return username;
    }
}

