package com.ps.order_service.service.impl;
import com.ps.order_service.feign.InventoryClient;
import com.ps.order_service.feign.NotificationClient;
import com.ps.order_service.feign.PaymentClient;
import com.ps.order_service.customExceptions.ResourceNotFoundException;
import com.ps.order_service.model.dto.common.PageResponseDTO;
import com.ps.order_service.model.dto.feign.PaymentRequestDTO;
import com.ps.order_service.model.dto.feign.RefundRequestDTO;
import com.ps.order_service.model.dto.feign.StockOperationRequestDTO;
import com.ps.order_service.model.dto.mapper.OrderDTOMapper;
import com.ps.order_service.model.dto.order.OrderRequestDTO;
import com.ps.order_service.model.dto.order.OrderResponseDTO;
import com.ps.order_service.model.entity.Order;
import com.ps.order_service.model.entity.OrderItem;
import com.ps.order_service.repository.OrderRepository;
import com.ps.order_service.service.OrderItemService;
import com.ps.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemService orderItemService;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = OrderDTOMapper.toEntity(request);

        List<OrderItem> orderItems = request.getOrderItemRequestDTOList()
                .stream()
                .map(orderItemService::createOrderItem)
                .toList();

        orderItems.forEach(item -> item.setOrder(order));

        order.setOrderItems(orderItems);

        BigDecimal totalAmount = orderItems.stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());
        return OrderDTOMapper.toDTO(orderRepo.save(order));
    }

    @Override
    @Transactional
    public OrderResponseDTO cancelOrder(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","Order Id",String.valueOf(orderId)));
        order.setStatus("CANCELLED");
        Map<Long,Integer> itemList = order.getOrderItems().stream().collect(Collectors.toMap(OrderItem::getProductId,OrderItem::getQuantity));
        for(Long itemId : itemList.keySet()){
            inventoryClient.releaseStock(StockOperationRequestDTO.builder().productId(itemId).quantity(itemList.get(itemId)).build());
        }
        if(order.getPaymentStatus().equals("SUCCESS")){
            paymentClient.refundPayment(RefundRequestDTO.builder().paymentId(order.getPaymentId()).orderId(orderId).refundAmount(order.getAmount()).build());
        }
        return OrderDTOMapper.toDTO(orderRepo.save(order));
    }

    @Override
    @Transactional
    public OrderResponseDTO updateStatus(Long orderId, String status) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","Order Id",String.valueOf(orderId)));
        order.setStatus(status);
        return OrderDTOMapper.toDTO(orderRepo.save(order));
    }
    @Override
    @Transactional
    public OrderResponseDTO updatePaymentStatus(Long orderId, String status) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","Order Id",String.valueOf(orderId)));
        order.setPaymentStatus(status);
        if(status.equals("SUCCESS")){
            Map<Long,Integer> itemList = order.getOrderItems().stream().collect(Collectors.toMap(OrderItem::getProductId,OrderItem::getQuantity));
            for(Long itemId : itemList.keySet()){
                inventoryClient.deductStock(StockOperationRequestDTO.builder().productId(itemId).quantity(itemList.get(itemId)).build());
            }
        }
        if(status.equals("FAILED")){
            Map<Long,Integer> itemList = order.getOrderItems().stream().collect(Collectors.toMap(OrderItem::getProductId,OrderItem::getQuantity));
            for(Long itemId : itemList.keySet()){
                inventoryClient.releaseStock(StockOperationRequestDTO.builder().productId(itemId).quantity(itemList.get(itemId)).build());
            }
        }
        return OrderDTOMapper.toDTO(orderRepo.save(order));
    }

    @Override
    @Transactional
    public OrderResponseDTO updatePaymentId(Long orderId, Long paymentId) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","Order Id",String.valueOf(orderId)));
        order.setPaymentId(paymentId);
        return OrderDTOMapper.toDTO(orderRepo.save(order));
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","Order Id",String.valueOf(orderId)));
        return OrderDTOMapper.toDTO(order);
    }

    @Override
    public PageResponseDTO<OrderResponseDTO> getAllOrders(int page_num,int page_size,Long userId) {
        Pageable pageable = PageRequest.of(page_num, page_size);
        Page<Order> productPage = orderRepo.findByUserId(userId,pageable);
        List<OrderResponseDTO> productList = productPage.getContent().stream().map(OrderDTOMapper::toDTO).toList();
        PageResponseDTO<OrderResponseDTO> response = new PageResponseDTO<>();
        response.setContent(productList);
        response.setPageNumber(productPage.getNumber());
        response.setPageSize(productPage.getSize());
        response.setTotalElements(productPage.getTotalElements());
        response.setTotalPages(productPage.getTotalPages());
        response.setLastPage(productPage.isLast());
        return response;
    }
}
