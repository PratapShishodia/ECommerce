package com.ps.order_service.client;

import com.ps.order_service.model.client.NotificationRequestDTO;
import com.ps.order_service.model.client.NotificationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-CLIENT")
public interface NotificationClient {
    @PostMapping("/notification/send")
    public NotificationResponseDTO sendNotification(@RequestBody NotificationRequestDTO requestDTO);
}
