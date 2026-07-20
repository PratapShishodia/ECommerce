package com.ps.payment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ORDER-CLIENT")
public interface OrderClient {
    @PutMapping("/order/updatePaymentStatus/{orderId}")
    void updatePaymentStatus(@PathVariable Long orderId, @RequestParam String status);
}
