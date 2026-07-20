package com.ps.order_service.client;

import com.ps.order_service.model.client.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {
    @GetMapping("api/product/{productId}")
    ProductResponseDTO getProduct(@PathVariable Long productId);
}
