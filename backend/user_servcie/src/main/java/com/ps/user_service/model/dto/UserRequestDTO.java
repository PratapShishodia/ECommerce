package com.ps.user_service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank(message = "First Name is Required")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Email is Required")
    @Email(message = "Email is not Valid")
    private String email;
    private String password;
    @NotBlank(message = "Mobile Number is Required")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;
    private String status;
    private String role;
    private String activationToken;
}
