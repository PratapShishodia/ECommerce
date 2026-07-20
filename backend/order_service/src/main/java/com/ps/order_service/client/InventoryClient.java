package com.ps.order_service.client;

import com.ps.order_service.model.client.InventoryResponseDTO;
import com.ps.order_service.model.client.StockOperationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-CLIENT")
public interface InventoryClient {
    @PatchMapping("/inventory/reserveStock")
    InventoryResponseDTO reserveStock(@RequestBody StockOperationRequestDTO requestDTO);
    @PatchMapping("/inventory/releaseStock")
    InventoryResponseDTO releaseStock(@RequestBody StockOperationRequestDTO requestDTO);
    @PatchMapping("/inventory/deductStock")
    InventoryResponseDTO deductStock(@RequestBody StockOperationRequestDTO requestDTO);
    @GetMapping("/checkAvailability")
    boolean checkAvailability(@RequestParam Long productId);
}
