package com.springpractice.client.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuctionException extends RuntimeException{
    private String code;
    private String message;
    public AuctionException(String message){
        super(message);
    }
}
