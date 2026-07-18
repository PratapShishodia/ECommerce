package com.ps.order_service;

import com.ps.order_service.model.dto.common.PageResponseDTO;
import com.ps.order_service.model.dto.order.OrderRequestDTO;
import com.ps.order_service.model.dto.order.OrderResponseDTO;
import com.ps.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public ResponseEntity<PageResponseDTO<OrderResponseDTO>> getAllOrder(@RequestParam int page_num, @RequestParam int page_size, @PathVariable Long userId){
        return ResponseEntity.ok(orderService.getAllOrders(page_num,page_size,userId));
    }

    @GetMapping("/orderId/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PutMapping("/updateStatus/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateStatus(@PathVariable Long orderId,@RequestParam String status){
        return ResponseEntity.ok(orderService.updateStatus(orderId,status));
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @PostMapping()
    public ResponseEntity<OrderResponseDTO> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequestDTO));
    }
}
