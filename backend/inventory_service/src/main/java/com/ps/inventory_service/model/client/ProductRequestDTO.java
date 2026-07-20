package com.ps.inventory_service.model.client;

import java.math.BigDecimal;

public class ProductRequestDTO {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productCategory;
    private String brandName;
    private String productImageUrl;
    private boolean active;
}
