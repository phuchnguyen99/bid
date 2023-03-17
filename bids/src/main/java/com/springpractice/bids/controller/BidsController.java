package com.springpractice.bids.controller;

import com.springpractice.bids.dto.BidRequest;
import com.springpractice.bids.service.BidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bids")
public class BidsController {

    private final BidService bidService;
    @PostMapping
    public String bidAnAuctionItem(@RequestBody BidRequest bidRequest){
        bidService.bidAnAuctionItem(bidRequest);
        return "Phuc";
    }
}
