package com.ps.payment_service.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RefundRequestDTO {
    private Long paymentId;
    private BigDecimal refundAmount;
    private String reason;
}