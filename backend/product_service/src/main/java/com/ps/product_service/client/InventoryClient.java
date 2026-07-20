package com.ps.product_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-CLIENT")
public interface InventoryClient {
    @GetMapping("/inventory/checkAvailability")
    Boolean checkAvailability(@RequestParam Long productId);
}
