package com.ps.payment_service.service;

import com.ps.payment_service.model.dto.*;
import com.ps.payment_service.model.dto.common.PageResponseDTO;

public interface PaymentService {
    PaymentResponseDTO initiatePayment(PaymentRequestDTO paymentRequestDTO);
    PaymentResponseDTO verifyPayment(PaymentVerificationRequestDTO verificationRequestDTO);
    PaymentResponseDTO getPaymentById(Long paymentId);
    PaymentResponseDTO getPaymentByOrderId(Long orderId);
    PageResponseDTO<PaymentResponseDTO> getPaymentHistory(int page_num,int page_size,Long userId);
    PaymentResponseDTO refundPayment(RefundRequestDTO refundRequestDTO);
    PaymentResponseDTO updatePaymentStatus(PaymentStatusRequestDTO paymentStatusRequestDTO);
}
