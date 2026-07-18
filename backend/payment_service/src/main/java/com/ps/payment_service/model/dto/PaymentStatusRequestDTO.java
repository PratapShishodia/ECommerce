package com.ps.payment_service.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentStatusRequestDTO {
    private Long paymentId;
    private String status;
}
