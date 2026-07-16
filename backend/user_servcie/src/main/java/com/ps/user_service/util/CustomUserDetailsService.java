package com.ps.user_service.util;

import com.ps.user_service.customExceptions.ResourceNotFoundException;
import com.ps.user_service.model.entity.Users;
import com.ps.user_service.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users  user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","username",username));
        return new CustomUserDetails(user);
    }
}
