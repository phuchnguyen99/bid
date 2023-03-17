package com.springpractice.notification.controller;

import com.springpractice.client.notification.NotificationRequest;
import com.springpractice.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping
    public void sendBidderNotificationEmail(@RequestBody NotificationRequest notificationRequest){
        notificationService.sendBidderNotification(notificationRequest);
    }
}
