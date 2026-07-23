package com.ps.payment_service.feign;

import com.ps.payment_service.model.dto.feign.NotificationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-SERVICE",configuration = FeignConfig.class)
public interface NotificationClient {

    @PostMapping("/api/notification/email")
    void sendEmail(@RequestBody NotificationRequestDTO request);
}