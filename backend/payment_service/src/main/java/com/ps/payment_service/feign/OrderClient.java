package com.ps.payment_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ORDER-SERVICE",configuration = FeignConfig.class)
public interface OrderClient {

    @PutMapping("/api/order/updatePaymentStatus/{orderId}")
    void updatePaymentStatus(@PathVariable Long orderId, @RequestParam String status);

    @PutMapping("/api/order/updatePaymentId/{orderId}")
    void updatePaymentID(@PathVariable Long orderId, @RequestParam Long paymentId);
}