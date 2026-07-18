package com.ps.order_service.service.impl;

import com.ps.order_service.customExceptions.ResourceNotFoundException;
import com.ps.order_service.model.dto.mapper.OrderItemDTOMapper;
import com.ps.order_service.model.dto.oderItem.OrderItemRequestDTO;
import com.ps.order_service.model.dto.oderItem.OrderItemResponseDTO;
import com.ps.order_service.model.entity.OrderItem;
import com.ps.order_service.repository.OrderItemRepository;
import com.ps.order_service.repository.OrderRepository;
import com.ps.order_service.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepo;
    private final OrderRepository orderRepo;

    @Override
    public OrderItem createOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem = OrderItemDTOMapper.toEntity(orderItemRequestDTO);
        orderItem.setSubTotal(orderItemRequestDTO.getPrice().multiply(BigDecimal.valueOf(orderItemRequestDTO.getQuantity())));
        return orderItem;
    }

    @Override
    public OrderItemResponseDTO getOrderItem(Long orderItemId) {
        return OrderItemDTOMapper.toDTO(orderItemRepo.findById(orderItemId).orElseThrow(()-> new ResourceNotFoundException("Order Item","Order Item Id",String.valueOf(orderItemId))));
    }
}
