package com.ps.product_service.model.dto.common;

import com.ps.product_service.model.dto.ProductResponseDTO;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private String statusCode;
    private String statusMsg;
    private ProductResponseDTO responseDTO;
}
