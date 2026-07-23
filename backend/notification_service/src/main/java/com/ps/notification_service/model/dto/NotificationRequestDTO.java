package com.ps.notification_service.model.dto;

import lombok.*;


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
