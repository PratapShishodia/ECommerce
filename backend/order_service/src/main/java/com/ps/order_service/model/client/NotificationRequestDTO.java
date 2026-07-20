package com.ps.order_service.model.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationRequestDTO {
    private Long userId;
    private String recipient;
    private String subject;
    private String message;
}

