package com.ps.order_service.model.client;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
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
