package com.springpractice.client.auction;

import com.springpractice.client.exception.CustomFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "auction",
        configuration = CustomFeignClientConfiguration.class
)
public interface AuctionClient {

    @GetMapping("api/v1/auctions/{auctionId}")
    AuctionItemResponse getAuctionItem(@PathVariable("auctionId") Long auctionId);

    @PutMapping("api/v1/auctions/updateAuctionItem/{auctionItem}")
    boolean updateAuctionItem(@PathVariable("auctionItem") String auctionItem,
                              @RequestBody AuctionBidItemRequest auctionBidItemRequest);
}
