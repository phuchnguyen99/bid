package com.springpractice.bids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.springpractice.bids",
        "com.springpractice.client",
        "com.springpractice.amqp"
})
public class BidsApplication {
    public static void main(String[] args){
        SpringApplication.run(BidsApplication.class, args);
    }
}
