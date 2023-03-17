package com.springpractice.notification.service;

import com.springpractice.client.notification.NotificationRequest;
import com.springpractice.notification.entity.Notification;
import com.springpractice.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationService {
    private final static String MESSAGE = "Your bid amount has been outbid with %f. Do you want to submit another bid?";
    private final NotificationRepository notificationRepository;
    public boolean sendBidderNotification(final NotificationRequest notificationRequest){
        log.info("Sending bidder a notification");
        notificationRepository.save(Notification.builder()
                .bidderName(notificationRequest.getBidderName())
                .bidderEmailAddress(String.format("bid_%s@bid.com", notificationRequest.getBidderName()))
                .message(String.format(MESSAGE, notificationRequest.getCurrentBid()))
                .build());
        log.info("Save and send bidder notification");
        return true;
    }
}
