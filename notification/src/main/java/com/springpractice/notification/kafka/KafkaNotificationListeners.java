//package com.springpractice.notification.kafka;
//
//import com.springpractice.client.notification.NotificationRequest;
//import com.springpractice.notification.service.NotificationService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@RequiredArgsConstructor
//@Component
//@Slf4j
//public class KafkaNotificationListeners {
//    private final NotificationService notificationService;
//
//    @KafkaListener(
//            topics = "bid",
//            groupId = "com.springpractice"
//    )
//    public void listener(final NotificationRequest notificationRequest){
//        log.info("Consuming notification request");
//        notificationService.sendBidderNotification(notificationRequest);
//        log.info("Consumed notification request");
//    }
//}
