package com.ps.payment_service.model.dto.feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentStatusDTO {
    private Long paymentId;
    private String status;
}
