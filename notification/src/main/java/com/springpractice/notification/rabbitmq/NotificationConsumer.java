package com.springpractice.notification.rabbitmq;

import com.springpractice.client.notification.NotificationRequest;
import com.springpractice.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationService notificationService;
    @RabbitListener( queues = "notification.queue")
    public void consumer(final NotificationRequest notificationRequest){
        log.info("Consuming notification request {}", notificationRequest);
        notificationService.sendBidderNotification(notificationRequest);
        log.info("Consumed notification request");
    }
}
