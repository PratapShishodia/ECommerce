package com.ps.payment_service.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentVerificationRequestDTO {
    private Long orderId;
    private Long paymentId;
}
