package com.ps.order_service.model.dto.feign;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {
    private Long orderId;
    private BigDecimal amount;
    private String paymentCurrency;
    private String paymentMethod;
}