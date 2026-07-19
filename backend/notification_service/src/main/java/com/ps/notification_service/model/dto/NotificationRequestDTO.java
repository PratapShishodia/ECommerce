package com.ps.notification_service.model.dto;

import com.ps.notification_service.model.enums.NotificationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {
    private Long userId;
    private String recipient;
    private String subject;
    private String message;
}
