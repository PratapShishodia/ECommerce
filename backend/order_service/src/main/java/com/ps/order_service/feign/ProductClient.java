package com.ps.order_service.feign;

import com.ps.order_service.model.dto.feign.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE",configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("/api/product/{productId}")
    ProductResponseDTO getProductById(@PathVariable Long productId);
}