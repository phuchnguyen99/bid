package com.springpractice.client.auction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String itemId;
    private String description;
}
