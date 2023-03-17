package com.springpractice.bids.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BidRequest {
    private Long auctionItemId;
    private double maxAutoBidAmount;
    private String bidderName;
}

