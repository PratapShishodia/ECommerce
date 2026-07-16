package com.ps.user_service.model.mapper;

import com.ps.user_service.model.dto.UserRequestDTO;
import com.ps.user_service.model.dto.UserResponseDTO;
import com.ps.user_service.model.entity.Users;

public class UserDTOMapper {
    public static UserResponseDTO toDTO(Users user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .isActive(user.isActive())
                .role(user.getRole())
                .mobileNumber(user.getMobileNumber())
                .build();
    }

    public static Users toEntity(UserRequestDTO dto) {
        return Users.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .mobileNumber(dto.getMobileNumber())
                .build();
    }
}
