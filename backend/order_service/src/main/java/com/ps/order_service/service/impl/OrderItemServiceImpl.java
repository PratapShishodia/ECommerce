package com.ps.order_service.service.impl;

import com.ps.order_service.client.InventoryClient;
import com.ps.order_service.client.ProductClient;
import com.ps.order_service.customExceptions.ResourceNotFoundException;
import com.ps.order_service.model.client.ProductResponseDTO;
import com.ps.order_service.model.client.StockOperationRequestDTO;
import com.ps.order_service.model.dto.mapper.OrderItemDTOMapper;
import com.ps.order_service.model.dto.oderItem.OrderItemRequestDTO;
import com.ps.order_service.model.dto.oderItem.OrderItemResponseDTO;
import com.ps.order_service.model.entity.OrderItem;
import com.ps.order_service.repository.OrderItemRepository;
import com.ps.order_service.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepo;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;

    @Override
    public OrderItem createOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
        ProductResponseDTO productResponseDTO = productClient.getProduct(orderItemRequestDTO.getProductId());
        if(productResponseDTO == null) {
            throw new ResourceNotFoundException("Product","Product Id",String.valueOf(orderItemRequestDTO.getProductId()));
        }
        if(inventoryClient.checkAvailability(orderItemRequestDTO.getProductId())){
            throw new RuntimeException("Out Of Stock");
        }
        OrderItem orderItem = OrderItemDTOMapper.toEntity(orderItemRequestDTO);
        orderItem.setPrice(productResponseDTO.getProductPrice());
        orderItem.setSubTotal(orderItemRequestDTO.getPrice().multiply(BigDecimal.valueOf(orderItemRequestDTO.getQuantity())));
        StockOperationRequestDTO stockOperationRequestDTO = new StockOperationRequestDTO();
        stockOperationRequestDTO.setProductId(orderItemRequestDTO.getProductId());
        stockOperationRequestDTO.setQuantity(orderItemRequestDTO.getQuantity());
        inventoryClient.reserveStock(stockOperationRequestDTO);
        return orderItem;
    }

    @Override
    public OrderItemResponseDTO getOrderItem(Long orderItemId) {
        return OrderItemDTOMapper.toDTO(orderItemRepo.findById(orderItemId).orElseThrow(()-> new ResourceNotFoundException("Order Item","Order Item Id",String.valueOf(orderItemId))));
    }
}
