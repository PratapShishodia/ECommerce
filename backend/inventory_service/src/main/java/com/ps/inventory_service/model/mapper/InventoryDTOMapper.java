package com.ps.inventory_service.model.mapper;

import com.ps.inventory_service.model.dto.InventoryRequestDTO;
import com.ps.inventory_service.model.dto.InventoryResponseDTO;
import com.ps.inventory_service.model.entity.Inventory;

public class InventoryDTOMapper {
    public static Inventory toEntity(InventoryRequestDTO requestDTO) {
        return Inventory.builder()
                .productId(requestDTO.getProductId())
                .quantity(requestDTO.getQuantity())
                .warehouseLocation(requestDTO.getWarehouseLocation())
                .build();
    }

    public static InventoryResponseDTO toDTO(Inventory inventory) {
        return InventoryResponseDTO.builder()
                .inventoryId(inventory.getInventoryId())
                .quantity(inventory.getQuantity())
                .warehouseLocation(inventory.getWarehouseLocation())
                .productId(inventory.getProductId())
                .reservedQuantity(inventory.getReservedQuantity())
                .availableQuantity(inventory.getAvailableQuantity())
                .build();
    }
}
