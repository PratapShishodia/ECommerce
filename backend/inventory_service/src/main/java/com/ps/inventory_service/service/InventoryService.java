package com.ps.inventory_service.service;

import com.ps.inventory_service.model.dto.BulkInventoryRequestDTO;
import com.ps.inventory_service.model.dto.InventoryRequestDTO;
import com.ps.inventory_service.model.dto.InventoryResponseDTO;
import com.ps.inventory_service.model.dto.StockOperationRequestDTO;

import java.util.List;

public interface InventoryService {
    InventoryResponseDTO createInventory(InventoryRequestDTO requestDTO);
    InventoryResponseDTO getInventoryByProductId(Long productId);
    InventoryResponseDTO updateStock(Long productId,InventoryRequestDTO requestDTO);
    InventoryResponseDTO reserveStock(StockOperationRequestDTO requestDTO);
    InventoryResponseDTO releaseStock(StockOperationRequestDTO requestDTO);
    InventoryResponseDTO deductStock(StockOperationRequestDTO requestDTO);
    boolean checkAvailability(Long productId);
    List<InventoryResponseDTO> bulkUpdate(BulkInventoryRequestDTO requestDTO);
}
