package com.ps.order_service.model.dto.mapper;

import com.ps.order_service.model.dto.order.OrderRequestDTO;
import com.ps.order_service.model.dto.order.OrderResponseDTO;
import com.ps.order_service.model.entity.Order;

public class OrderDTOMapper {
    public static OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .amount(order.getAmount())
                .status(order.getStatus())
                .paymentStatus(order.getPaymentStatus())
                .orderItems(order.getOrderItems().stream().map(OrderItemDTOMapper::toDTO).toList())
                .build();
    }

    public static Order toEntity(OrderRequestDTO requestDTO) {
        return Order.builder()
                .userId(requestDTO.getUserId())
                .status(requestDTO.getStatus())
                .paymentStatus(requestDTO.getPaymentStatus())
                .build();
    }
}
