package com.springpractice.bids.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BidsException extends Exception{
    private String message;
    private String code;
    public BidsException(String message) {
        super(message);
    }
}
