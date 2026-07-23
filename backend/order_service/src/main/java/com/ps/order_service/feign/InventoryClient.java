package com.ps.order_service.feign;

import com.ps.order_service.model.dto.feign.StockOperationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "INVENTORY-SERVICE",configuration = FeignConfig.class)
public interface InventoryClient {

    @GetMapping("/api/inventory/checkAvailability")
    Boolean checkAvailability(@RequestParam Long productId);

    @PutMapping("/api/inventory/reserveStock")
    void reserveStock(@RequestBody StockOperationRequestDTO request);

    @PutMapping("/api/inventory/releaseStock")
    void releaseStock(@RequestBody StockOperationRequestDTO request);

    @PutMapping("/api/inventory/deductStock")
    void deductStock(@RequestBody StockOperationRequestDTO requestDTO);
}