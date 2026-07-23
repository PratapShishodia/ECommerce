package com.ps.order_service.feign;

import com.ps.order_service.model.dto.feign.PaymentRequestDTO;
import com.ps.order_service.model.dto.feign.PaymentResponseDTO;
import com.ps.order_service.model.dto.feign.RefundRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE",configuration = FeignConfig.class)
public interface PaymentClient {

    @PostMapping("/api/payment/initiate")
    PaymentResponseDTO initiatePayment(@RequestBody PaymentRequestDTO request);

    @PostMapping("/api/payment/refund")
    void refundPayment(@RequestBody RefundRequestDTO requestDTO);
}