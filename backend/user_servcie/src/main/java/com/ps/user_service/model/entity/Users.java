package com.ps.user_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    @Column(nullable = false,unique = true)
    private String mobileNumber;
    private boolean isActive;
    private String role;
    private String activationToken;
    private String OTP;
    private LocalDateTime OTP_EXPIRATION;
    private boolean isOTPVerified;
}
