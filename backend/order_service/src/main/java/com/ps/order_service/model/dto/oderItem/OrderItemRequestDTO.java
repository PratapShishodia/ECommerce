package com.ps.order_service.model.dto.oderItem;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequestDTO {
    private Long productId;
    private int quantity;
    private BigDecimal price;
}
