package com.ps.notification_service.model.entity;

import com.ps.notification_service.model.enums.NotificationStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "notifications_log")
public class Notification {
    @Id
    private String notificationId;
    private Long userId;
    private String recipient;
    private String subject;
    private String message;
    private String type;
    private NotificationStatus status;
    private LocalDateTime sentAt;
}
