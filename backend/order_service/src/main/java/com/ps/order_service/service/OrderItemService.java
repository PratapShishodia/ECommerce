package com.ps.order_service.service;

import com.ps.order_service.model.dto.oderItem.OrderItemRequestDTO;
import com.ps.order_service.model.dto.oderItem.OrderItemResponseDTO;
import com.ps.order_service.model.entity.OrderItem;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItemRequestDTO orderItemRequestDTO);
    OrderItemResponseDTO getOrderItem(Long orderItemId);
}
