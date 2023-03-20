package com.springpractice.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.springpractice.notification",
        "com.springpractice.amqp"
})
public class NotificationApplication {
    public static void main(String[] args){
        SpringApplication.run(NotificationApplication.class, args);
    }
}
