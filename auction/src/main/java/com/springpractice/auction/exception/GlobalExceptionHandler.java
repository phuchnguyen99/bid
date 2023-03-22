package com.springpractice.auction.exception;

import com.springpractice.client.exception.AuctionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuctionException.class)
    protected ResponseEntity handleGlobalException(AuctionException auctionException){
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code(auctionException.getCode())
                        .message(auctionException.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(final Exception e, final Locale locale){
        return ResponseEntity
                .badRequest()
                .body("Exception occurs inside the API: " + e);
    }
}
