package com.ps.order_service.model.client;

public class InventoryResponseDTO {
    private String inventoryId;
    private Long productId;
    private Integer quantity;
    private Integer reservedQuantity;
    private Integer availableQuantity;
    private String warehouseLocation;
}
