package com.ps.notification_service.service.impl;

import com.ps.notification_service.customExceptions.ResourceNotFoundException;
import com.ps.notification_service.model.dto.NotificationRequestDTO;
import com.ps.notification_service.model.dto.NotificationResponseDTO;
import com.ps.notification_service.model.dto.common.PageResponseDTO;
import com.ps.notification_service.model.entity.Notification;
import com.ps.notification_service.model.enums.NotificationStatus;
import com.ps.notification_service.model.mapper.NotificationDTOMapper;
import com.ps.notification_service.repository.NotificationRepo;
import com.ps.notification_service.service.EmailService;
import com.ps.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo  notificationRepo;
    private final EmailService emailService;

    @Override
    public NotificationResponseDTO sendEmail(NotificationRequestDTO requestDTO) {
        Notification notification = NotificationDTOMapper.toEntity(requestDTO);
        notification.setNotificationId(UUID.randomUUID().toString().split("-")[0]);
        notification.setStatus(NotificationStatus.PENDING);
        notification.setType("EMAIL");
        notification = notificationRepo.save(notification);
        boolean mailSent = emailService.sendEmail(requestDTO.getRecipient(), requestDTO.getSubject(), requestDTO.getMessage());
        if(mailSent) {
            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());
            return NotificationDTOMapper.toDTO(notificationRepo.save(notification));
        }
        else {
            notification.setStatus(NotificationStatus.FAILED);
            notificationRepo.save(notification);
            throw new RuntimeException("Unable to send email");
        }
    }

    @Override
    public NotificationResponseDTO getNotificationById(String notificationId) {
        return NotificationDTOMapper.toDTO(notificationRepo.findById(notificationId).orElseThrow(() -> new ResourceNotFoundException("Notification","Notification Id",String.valueOf(notificationId))));
    }

    @Override
    public PageResponseDTO<NotificationResponseDTO> getNotificationsByUser(int page_num, int page_size, Long userId) {
        Pageable pageable = PageRequest.of(page_num, page_size);
        Page<Notification> paymentPage = notificationRepo.findByUserId(userId,pageable);
        List<NotificationResponseDTO> paymentList = paymentPage.getContent().stream().map(NotificationDTOMapper::toDTO).toList();
        PageResponseDTO<NotificationResponseDTO> response = new PageResponseDTO<>();
        response.setContent(paymentList);
        response.setPageNumber(paymentPage.getNumber());
        response.setPageSize(paymentPage.getSize());
        response.setTotalElements(paymentPage.getTotalElements());
        response.setTotalPages(paymentPage.getTotalPages());
        response.setLastPage(paymentPage.isLast());
        return response;
    }

    @Override
    public NotificationResponseDTO updateNotificationStatus(String notificationId, NotificationStatus status) {
        Notification notification = notificationRepo.findById(notificationId).orElseThrow(() -> new ResourceNotFoundException("Notification","Notification Id",String.valueOf(notificationId)));
        notification.setStatus(status);
        return NotificationDTOMapper.toDTO(notificationRepo.save(notification));
    }
}
