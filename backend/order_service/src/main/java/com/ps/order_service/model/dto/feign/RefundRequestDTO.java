package com.ps.order_service.model.dto.feign;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RefundRequestDTO {
    private Long orderId;
    private Long paymentId;
    private BigDecimal refundAmount;
}