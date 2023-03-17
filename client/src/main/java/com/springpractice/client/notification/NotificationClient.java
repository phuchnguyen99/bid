package com.springpractice.client.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification"
)
public interface NotificationClient {
    @PostMapping("/api/v1/notification")
    void sendBidderNotificationEmail(@RequestBody NotificationRequest notificationRequest);
}
