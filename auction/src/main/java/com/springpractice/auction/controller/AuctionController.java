package com.springpractice.auction.controller;


import com.springpractice.auction.dto.AuctionItemRequest;
import com.springpractice.auction.service.AuctionService;
import com.springpractice.client.auction.AuctionBidItemRequest;
import com.springpractice.client.auction.AuctionItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/auctions")
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping
    public String addAuctionItem(@RequestBody AuctionItemRequest auctionItemRequest){
        final Long auctionId = auctionService.addAuctionItem(auctionItemRequest);
        log.info("Auction id {} has been created", auctionId);
        return auctionId.toString();
    }

    @GetMapping
    public List<AuctionItemResponse> getAllAuctionItems()
    {
        return auctionService.getAllAuctionItems();
    }

    @GetMapping("/{auctionItemId}")
    public AuctionItemResponse getAuctionItem(@PathVariable("auctionItemId") String auctionItemId){
        return auctionService.getAuctionItem(auctionItemId);
    }


    @PutMapping("/updateAuctionItem/{auctionItem}")
    public boolean updateAuctionItem(@PathVariable("auctionItem") String auctionItem,
                                     @RequestBody AuctionBidItemRequest auctionBidItemRequest){
        log.info("Updating item {}", auctionItem);
        auctionService.updateBidOnAuctionItem(auctionItem, auctionBidItemRequest);
        log.info("Updated item {}", auctionItem);
        return true;
    }
}
