package com.ps.notification_service.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponseDTO{
    private Long userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobileNumber;
    private String role;
}
