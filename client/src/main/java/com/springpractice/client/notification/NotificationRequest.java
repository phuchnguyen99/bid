package com.springpractice.client.notification;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class NotificationRequest {
    private final String bidderName;
    private final double currentBid;
}
