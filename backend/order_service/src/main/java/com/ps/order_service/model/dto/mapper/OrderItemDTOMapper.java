package com.ps.order_service.model.dto.mapper;

import com.ps.order_service.model.dto.oderItem.OrderItemRequestDTO;
import com.ps.order_service.model.dto.oderItem.OrderItemResponseDTO;
import com.ps.order_service.model.entity.OrderItem;

public class OrderItemDTOMapper {
    public static OrderItemResponseDTO toDTO(OrderItem orderItem) {
        return OrderItemResponseDTO.builder()
                .orderItemId(orderItem.getOrderItemId())
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .subTotal(orderItem.getSubTotal())
                .build();
    }

    public static OrderItem toEntity(OrderItemRequestDTO requestDTO) {
        return OrderItem.builder()
                .productId(requestDTO.getProductId())
                .quantity(requestDTO.getQuantity())
                .price(requestDTO.getPrice())
                .build();
    }
}
