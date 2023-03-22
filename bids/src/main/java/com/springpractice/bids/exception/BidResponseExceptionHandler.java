package com.springpractice.bids.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@ResponseStatus
public class BidResponseExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(BidsException.class)
    public ResponseEntity<ErrorMessage> handleBidException(BidsException bidsException,
                                                     WebRequest request){
        return ResponseEntity.
                badRequest()
                .body(ErrorMessage.builder()
                        .message(bidsException.getMessage())
                        .code(bidsException.getCode())
                        .build());
    }
}
