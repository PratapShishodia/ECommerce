package com.ps.user_service.model.dto.common;

import com.ps.user_service.model.dto.UserResponseDTO;

public record LoginResponseDTO(String message, UserResponseDTO responseDTO, String refreshToken,String accessToken) {
}
