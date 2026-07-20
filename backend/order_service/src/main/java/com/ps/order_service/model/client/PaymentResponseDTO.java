package com.ps.order_service.model.client;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class PaymentResponseDTO {
    private Long userId;
    private Long paymentId;
    private Long orderId;
    private UUID transactionId;
    private BigDecimal amount;
    private String paymentCurrency;
    private String paymentMethod;
    private String status;
    private LocalDateTime paymentDate;
}
