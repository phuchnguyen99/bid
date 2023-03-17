package com.springpractice.auction.dto;

import com.springpractice.client.auction.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuctionItemRequest {
    private double reservePrice;
    private Item item;
}
