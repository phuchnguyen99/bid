package com.springpractice.client.auction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuctionBidItemRequest {
    private final String bidderName;
    private final double currentBid;
}
