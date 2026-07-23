package com.ps.order_service.service.impl;

import com.ps.order_service.customExceptions.ResourceNotFoundException;
import com.ps.order_service.feign.InventoryClient;
import com.ps.order_service.feign.ProductClient;
import com.ps.order_service.model.dto.feign.ProductResponseDTO;
import com.ps.order_service.model.dto.feign.StockOperationRequestDTO;
import com.ps.order_service.model.dto.mapper.OrderItemDTOMapper;
import com.ps.order_service.model.dto.oderItem.OrderItemRequestDTO;
import com.ps.order_service.model.dto.oderItem.OrderItemResponseDTO;
import com.ps.order_service.model.entity.OrderItem;
import com.ps.order_service.repository.OrderItemRepository;
import com.ps.order_service.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepo;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;


    @Override
    @Transactional
    public OrderItem createOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
//        Find Product
        ProductResponseDTO productResponseDTO = productClient.getProductById(orderItemRequestDTO.getProductId());
//        Check Availability
        if(!inventoryClient.checkAvailability(productResponseDTO.getProductId())) {
            throw new RuntimeException("Out of Stock");
        }

        OrderItem orderItem = OrderItemDTOMapper.toEntity(orderItemRequestDTO);
        orderItem.setPrice(productResponseDTO.getProductPrice());
        orderItem.setSubTotal(productResponseDTO.getProductPrice().multiply(BigDecimal.valueOf(orderItemRequestDTO.getQuantity())));

//        Reserve Stock
        inventoryClient.reserveStock(StockOperationRequestDTO.builder().productId(orderItem.getProductId()).quantity(orderItemRequestDTO.getQuantity()).build());
        return orderItem;
    }

    @Override
    public OrderItemResponseDTO getOrderItem(Long orderItemId) {
        return OrderItemDTOMapper.toDTO(orderItemRepo.findById(orderItemId).orElseThrow(()-> new ResourceNotFoundException("Order Item","Order Item Id",String.valueOf(orderItemId))));
    }
}
