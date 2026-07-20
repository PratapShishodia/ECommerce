package com.ps.order_service.model.client;

import com.ps.order_service.model.enums.NotificationStatus;

import java.time.LocalDateTime;

public class NotificationResponseDTO {
    private String notificationId;
    private Long userId;
    private String recipient;
    private String subject;
    private String message;
    private String type;
    private NotificationStatus status;
    private LocalDateTime sentAt;
}

