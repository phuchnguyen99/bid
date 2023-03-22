package com.springpractice.auction.exception;

import com.springpractice.client.exception.AuctionException;

public class InvalidAuctionItemException extends AuctionException {
    public InvalidAuctionItemException(String message, String code){
        super(message, code);
    }
}
