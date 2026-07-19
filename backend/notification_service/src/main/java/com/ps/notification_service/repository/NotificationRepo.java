package com.ps.notification_service.repository;

import com.ps.notification_service.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends MongoRepository<Notification, String> {
    Page<Notification> findByUserId(Long userId, Pageable pageable);
}
