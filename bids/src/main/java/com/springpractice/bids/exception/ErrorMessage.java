package com.springpractice.bids.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ErrorMessage {
    private String code;
    private String message;
}
