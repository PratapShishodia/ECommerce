package com.ps.payment_service.service.impl;

import com.ps.payment_service.customExceptions.ResourceNotFoundException;
import com.ps.payment_service.model.dto.*;
import com.ps.payment_service.model.dto.common.PageResponseDTO;
import com.ps.payment_service.model.entity.Payment;
import com.ps.payment_service.model.mapper.PaymentDTOMapper;
import com.ps.payment_service.repository.PaymentRepo;
import com.ps.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;

    @Override
    public PaymentResponseDTO initiatePayment(PaymentRequestDTO paymentRequestDTO) {
        Payment  payment = PaymentDTOMapper.toEntity(paymentRequestDTO);
        payment.setTransactionId(UUID.randomUUID());
        payment.setStatus("PENDING");
        return PaymentDTOMapper.toDTO(paymentRepo.save(payment));
    }

    @Override
    public PaymentResponseDTO verifyPayment(PaymentVerificationRequestDTO verificationRequestDTO) {
        Payment payment = paymentRepo.findById(verificationRequestDTO.getPaymentId()).orElseThrow(()-> new ResourceNotFoundException("Payment Details","Payment ID",String.valueOf(verificationRequestDTO.getPaymentId())));
        payment.setStatus("SUCCESS");
        return PaymentDTOMapper.toDTO(paymentRepo.save(payment));
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long paymentId) {
        return PaymentDTOMapper.toDTO(paymentRepo.findById(paymentId).orElseThrow(()-> new ResourceNotFoundException("Payment Details","Payment ID",String.valueOf(paymentId))));
    }

    @Override
    public PaymentResponseDTO getPaymentByOrderId(Long orderId) {
        return PaymentDTOMapper.toDTO(paymentRepo.findByOrderId(orderId).orElseThrow(()-> new ResourceNotFoundException("Payment Details","Payment ID",String.valueOf(orderId))));
    }

    @Override
    public PageResponseDTO<PaymentResponseDTO> getPaymentHistory(int page_num,int page_size,Long userId) {
        Pageable pageable = PageRequest.of(page_num, page_size);
        Page<Payment> paymentPage = paymentRepo.findByUserId(userId,pageable);
        List<PaymentResponseDTO> paymentList = paymentPage.getContent().stream().map(PaymentDTOMapper::toDTO).toList();
        PageResponseDTO<PaymentResponseDTO> response = new PageResponseDTO<>();
        response.setContent(paymentList);
        response.setPageNumber(paymentPage.getNumber());
        response.setPageSize(paymentPage.getSize());
        response.setTotalElements(paymentPage.getTotalElements());
        response.setTotalPages(paymentPage.getTotalPages());
        response.setLastPage(paymentPage.isLast());
        return response;
    }

    @Override
    public PaymentResponseDTO refundPayment(RefundRequestDTO refundRequestDTO) {
        Payment payment = paymentRepo.findById(refundRequestDTO.getPaymentId()).orElseThrow(()->new ResourceNotFoundException("Payment Details","Payment ID",String.valueOf(refundRequestDTO.getPaymentId())));
        payment.setStatus("REFUNDED");
        return PaymentDTOMapper.toDTO(paymentRepo.save(payment));
    }

    @Override
    public PaymentResponseDTO updatePaymentStatus(PaymentStatusRequestDTO paymentStatusRequestDTO) {
        Payment payment = paymentRepo.findById(paymentStatusRequestDTO.getPaymentId()).orElseThrow(()->new ResourceNotFoundException("Payment Details","Payment ID",String.valueOf(paymentStatusRequestDTO.getPaymentId())));
        payment.setStatus(paymentStatusRequestDTO.getStatus());
        return PaymentDTOMapper.toDTO(paymentRepo.save(payment));
    }
}
