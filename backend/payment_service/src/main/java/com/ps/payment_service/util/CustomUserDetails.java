package com.ps.payment_service.util;

import com.ps.payment_service.model.dto.UsersResponseDTO;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final UsersResponseDTO user;

    public CustomUserDetails(UsersResponseDTO user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
    }

    public @Nullable String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getEmail();
    }

    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public UsersResponseDTO getUser() {
        return user;
    }
}
