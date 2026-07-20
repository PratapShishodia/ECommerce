package com.ps.inventory_service.client;

import com.ps.inventory_service.model.client.ProductRequestDTO;
import com.ps.inventory_service.model.client.ProductResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PRODUCT-CLIENT")
public interface ProductClient {
    @GetMapping("/product/{productId}")
    ProductResponseDTO fetchById(@PathVariable Long productId);

    @PutMapping("/product/update/{productId}")
    void updateProduct(@PathVariable Long productId,@Valid @RequestBody ProductRequestDTO requestDTO);
}
