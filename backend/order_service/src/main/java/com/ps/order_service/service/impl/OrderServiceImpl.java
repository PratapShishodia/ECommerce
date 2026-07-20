package com.ps.order_service.service.impl;

import com.ps.order_service.client.InventoryClient;
import com.ps.order_service.client.NotificationClient;
import com.ps.order_service.client.PaymentClient;
import com.ps.order_service.customExceptions.ResourceNotFoundException;
import com.ps.order_service.model.client.*;
import com.ps.order_service.model.dto.common.PageResponseDTO;
import com.ps.order_service.model.dto.mapper.OrderDTOMapper;
import com.ps.order_service.model.dto.mapper.OrderItemDTOMapper;
import com.ps.order_service.model.dto.order.OrderRequestDTO;
import com.ps.order_service.model.dto.order.OrderResponseDTO;
import com.ps.order_service.model.entity.Order;
import com.ps.order_service.model.entity.OrderItem;
import com.ps.order_service.repository.OrderRepository;
import com.ps.order_service.service.OrderItemService;
import com.ps.order_service.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemService orderItemService;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;

    @Override
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
        Order savedOrder = orderRepo.save(order);
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        try{
            PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
            paymentRequestDTO.setUserId(request.getUserId());
            paymentRequestDTO.setOrderId(savedOrder.getOrderId());
            paymentRequestDTO.setAmount(order.getAmount());
            paymentRequestDTO.setPaymentCurrency("INR");
            paymentRequestDTO.setPaymentMethod("UPI");
            responseDTO = paymentClient.initiatePayment(paymentRequestDTO);
        }
        catch(RuntimeException e){
            throw e;
        }
        savedOrder.setPaymentStatus("PENDING");
        PaymentVerificationRequestDTO  paymentVerificationRequestDTO = new PaymentVerificationRequestDTO();
        paymentVerificationRequestDTO.setPaymentId(responseDTO.getPaymentId());
        paymentVerificationRequestDTO.setTransactionId(String.valueOf(responseDTO.getTransactionId()));
        PaymentResponseDTO responseDTO1 = paymentClient.verifyPayment(paymentVerificationRequestDTO);

        

        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO();
        notificationRequestDTO.setUserId(request.getUserId());
        notificationRequestDTO.setMessage( "Dear User,\n\nYour order #ORD-"+savedOrder.getOrderId()+" has been placed successfully. Thank you for shopping with us!\n\nRegards,\nE-Commerce Team");
        notificationRequestDTO.setRecipient("pratapshishodia22@gmail.com");
        notificationRequestDTO.setSubject("Order #"+savedOrder.getOrderId());
        notificationClient.sendNotification(notificationRequestDTO);
        return OrderDTOMapper.toDTO(savedOrder);

    }

    @Override
    public OrderResponseDTO cancelOrder(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","Order Id",String.valueOf(orderId)));
        order.setStatus("CANCELLED");
        return OrderDTOMapper.toDTO(orderRepo.save(order));
    }

    @Override
    public OrderResponseDTO updateStatus(Long orderId, String status) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","Order Id",String.valueOf(orderId)));
        order.setStatus(status);
        return OrderDTOMapper.toDTO(orderRepo.save(order));
    }
    @Override
    public OrderResponseDTO updatePaymentStatus(Long orderId, String status) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order","Order Id",String.valueOf(orderId)));
        order.setPaymentStatus(status);
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
