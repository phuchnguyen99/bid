package com.springpractice.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void publish(Object payload, String exchange, String routingKey){
        log.info("Publishing to {} using routing key {}. Payload {}",
                exchange, routingKey, payload);
        rabbitTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routing key {}. Payload {}",
                exchange, routingKey, payload);
    }
}
