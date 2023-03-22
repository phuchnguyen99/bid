package com.springpractice.auction.exception;

import com.springpractice.client.exception.AuctionException;

public class EntityAuctionItemException extends AuctionException {
    public EntityAuctionItemException(String message, String code){
        super(message, code);
    }
}
