package com.ps.order_service.model.dto.order;

import com.ps.order_service.model.dto.oderItem.OrderItemRequestDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {
    private Long userId;
    private String status;
    private String paymentStatus;
    private List<OrderItemRequestDTO> orderItemRequestDTOList;
}
