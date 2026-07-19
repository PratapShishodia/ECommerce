package com.ps.notification_service.service;

import com.ps.notification_service.model.dto.NotificationRequestDTO;
import com.ps.notification_service.model.dto.NotificationResponseDTO;
import com.ps.notification_service.model.dto.common.PageResponseDTO;
import com.ps.notification_service.model.enums.NotificationStatus;

import java.util.List;

public interface NotificationService {
    NotificationResponseDTO sendEmail(NotificationRequestDTO requestDTO);
    NotificationResponseDTO getNotificationById(String notificationId);
    PageResponseDTO<NotificationResponseDTO> getNotificationsByUser(int page_num,int page_size,Long userId);
    NotificationResponseDTO updateNotificationStatus(String notificationId, NotificationStatus status);
}
