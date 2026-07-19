package com.ps.notification_service.model.mapper;

import com.ps.notification_service.model.dto.NotificationRequestDTO;
import com.ps.notification_service.model.dto.NotificationResponseDTO;
import com.ps.notification_service.model.entity.Notification;

public class NotificationDTOMapper {
    public static NotificationResponseDTO toDTO(Notification notification) {
        return NotificationResponseDTO.builder()
                .notificationId(notification.getNotificationId())
                .userId(notification.getUserId())
                .recipient(notification.getRecipient())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .type(notification.getType())
                .status(notification.getStatus())
                .sentAt(notification.getSentAt())
                .build();
    }

    public static Notification toEntity(NotificationRequestDTO requestDTO) {
        return Notification.builder()
                .userId(requestDTO.getUserId())
                .recipient(requestDTO.getRecipient())
                .subject(requestDTO.getSubject())
                .message(requestDTO.getMessage())
                .build();
    }
}
