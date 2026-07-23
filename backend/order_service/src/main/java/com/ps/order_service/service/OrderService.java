package com.ps.order_service.service;

import com.ps.order_service.model.dto.common.PageResponseDTO;
import com.ps.order_service.model.dto.order.OrderRequestDTO;
import com.ps.order_service.model.dto.order.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO cancelOrder(Long orderId);
    OrderResponseDTO updateStatus(Long orderId,String status);
    OrderResponseDTO getOrderById(Long orderId);
   PageResponseDTO<OrderResponseDTO> getAllOrders(int page_num,int page_size,Long userId);
    OrderResponseDTO updatePaymentStatus(Long orderId, String status);
    OrderResponseDTO updatePaymentId(Long orderId, Long paymentId);
}
