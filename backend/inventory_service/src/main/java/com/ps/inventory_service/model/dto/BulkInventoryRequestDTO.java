package com.ps.inventory_service.model.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BulkInventoryRequestDTO {
    private List<InventoryRequestDTO> inventories;
}
