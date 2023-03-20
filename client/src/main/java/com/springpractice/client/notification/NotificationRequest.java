package com.springpractice.client.notification;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NotificationRequest {
    private String bidderName;
    private double currentBid;
}
