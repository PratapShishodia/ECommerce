package com.ps.order_service.model.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockOperationRequestDTO {
    private Long productId;
    private Integer quantity;
}
