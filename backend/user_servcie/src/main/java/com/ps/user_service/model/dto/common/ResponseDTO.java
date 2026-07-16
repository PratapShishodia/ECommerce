package com.ps.user_service.model.dto.common;

import com.ps.user_service.model.dto.UserResponseDTO;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private String statusCode;
    private String statusMsg;
    private UserResponseDTO responseDTO;
}
