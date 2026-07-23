package com.ps.product_service.feign;

import com.ps.product_service.model.dto.feign.InventoryRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "INVENTORY-SERVICE",configuration = FeignConfig.class)
public interface InventoryClient {

    @PostMapping("/api/inventory/add")
    void addInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO);

    @PutMapping("/api/inventory/updateStock")
   void updateStock(@RequestParam Long productId, @RequestBody InventoryRequestDTO requestDTO);
}