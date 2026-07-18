package com.ps.order_service.model.dto.oderItem;

import com.ps.order_service.model.entity.Order;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponseDTO {
    private Long orderItemId;
    private Long productId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}
