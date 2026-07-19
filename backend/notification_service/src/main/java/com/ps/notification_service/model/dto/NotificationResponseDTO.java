package com.ps.notification_service.model.dto;

import com.ps.notification_service.model.enums.NotificationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
