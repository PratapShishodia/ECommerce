package com.ps.order_service.client;

import com.ps.order_service.model.client.PaymentRequestDTO;
import com.ps.order_service.model.client.PaymentResponseDTO;
import com.ps.order_service.model.client.PaymentVerificationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FEIGN-CLIENT")
public interface PaymentClient {
    @PostMapping("/payment/initiate")
    public PaymentResponseDTO initiatePayment(@RequestBody PaymentRequestDTO paymentRequestDTO);
    @PostMapping("/payment/verify")
    public PaymentResponseDTO verifyPayment(@RequestBody PaymentVerificationRequestDTO requestDTO);
}
