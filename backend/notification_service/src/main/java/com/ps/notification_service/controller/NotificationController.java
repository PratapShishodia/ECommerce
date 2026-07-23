package com.ps.notification_service.controller;

import com.ps.notification_service.model.dto.NotificationRequestDTO;
import com.ps.notification_service.model.dto.NotificationResponseDTO;
import com.ps.notification_service.model.dto.common.PageResponseDTO;
import com.ps.notification_service.model.enums.NotificationStatus;
import com.ps.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping
    public ResponseEntity<NotificationResponseDTO> getNotificationStatus(@RequestParam String notificationId) {
        return ResponseEntity.ok(notificationService.getNotificationById(notificationId));
    }
    @GetMapping("/users")
    public ResponseEntity<PageResponseDTO<NotificationResponseDTO>> getNotificationsByUser(@RequestParam int page_num,@RequestParam int page_size,@RequestParam Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUser(page_num,page_size,userId));
    }

    @PostMapping("/email")
    public ResponseEntity<NotificationResponseDTO> sendNotification(@RequestBody NotificationRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.sendEmail(requestDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<NotificationResponseDTO> updateNotification(@RequestParam String notificationId,@RequestParam NotificationStatus status) {
        return ResponseEntity.ok(notificationService.updateNotificationStatus(notificationId,status));
    }
}
