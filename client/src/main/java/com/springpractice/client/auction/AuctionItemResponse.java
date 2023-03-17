package com.springpractice.client.auction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionItemResponse {
    private Long auctionItemId;
    private double reservePrice;

    private double currentBid;
    private String bidderName;

    private Item item;
}
