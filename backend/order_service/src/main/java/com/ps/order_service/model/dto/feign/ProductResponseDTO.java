package com.ps.order_service.model.dto.feign;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productCategory;
    private String brandName;
    private String productImageUrl;
    private boolean active;
}