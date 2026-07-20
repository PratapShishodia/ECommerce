package com.ps.payment_service.client;

import com.ps.payment_service.model.client.NotificationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-CLIENT")
public interface NotificationClient {
    @PostMapping("/notification/send")
    void sendNotification(@RequestBody NotificationRequestDTO requestDTO);
}
