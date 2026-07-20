package com.ps.order_service.model.client;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PaymentRequestDTO {
    private Long userId;
    private Long orderId;
    private BigDecimal amount;
    private String paymentCurrency;
    private String paymentMethod;
}
