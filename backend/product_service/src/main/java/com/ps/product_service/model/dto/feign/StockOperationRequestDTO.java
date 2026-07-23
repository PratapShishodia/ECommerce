package com.ps.product_service.model.dto.feign;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockOperationRequestDTO {
    private Long productId;
    private Integer quantity;
}