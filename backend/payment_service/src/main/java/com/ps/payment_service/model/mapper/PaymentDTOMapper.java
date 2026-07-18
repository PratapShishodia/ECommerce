package com.ps.payment_service.model.mapper;

import com.ps.payment_service.model.dto.PaymentRequestDTO;
import com.ps.payment_service.model.dto.PaymentResponseDTO;
import com.ps.payment_service.model.entity.Payment;

public class PaymentDTOMapper {

    public static PaymentResponseDTO toDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .userId(payment.getUserId())
                .paymentId(payment.getPaymentId())
                .orderId(payment.getOrderId())
                .transactionId(payment.getTransactionId())
                .amount(payment.getAmount())
                .paymentCurrency(payment.getPaymentCurrency())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .build();
    }

    public static Payment toEntity(PaymentRequestDTO paymentRequestDTO) {
        return Payment.builder()
                .orderId(paymentRequestDTO.getOrderId())
                .userId(paymentRequestDTO.getUserId())
                .amount(paymentRequestDTO.getAmount())
                .paymentCurrency(paymentRequestDTO.getPaymentCurrency())
                .paymentMethod(paymentRequestDTO.getPaymentMethod())
                .build();
    }

}
