package com.ps.order_service.model.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentVerificationRequestDTO {
    private Long paymentId;
    private String transactionId;
}
