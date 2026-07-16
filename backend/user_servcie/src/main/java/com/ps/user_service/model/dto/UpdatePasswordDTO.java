package com.ps.user_service.model.dto;

public record UpdatePasswordDTO(String oldPassword, String newPassword) {
}
