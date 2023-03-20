package com.springpractice.bids.service;

import com.springpractice.amqp.RabbitMQMessageProducer;
import com.springpractice.bids.dto.BidRequest;
import com.springpractice.client.auction.AuctionBidItemRequest;
import com.springpractice.client.auction.AuctionClient;
import com.springpractice.client.auction.AuctionItemResponse;
import com.springpractice.client.notification.NotificationClient;
import com.springpractice.client.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class BidService {

    private final  AuctionClient auctionClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void bidAnAuctionItem(final BidRequest bidRequest) {
        final AuctionItemResponse response =
                auctionClient.getAuctionItem(bidRequest.getAuctionItemId());
        if(response.getReservePrice() < bidRequest.getMaxAutoBidAmount()
          && response.getCurrentBid() < bidRequest.getMaxAutoBidAmount()){
            auctionClient.updateAuctionItem(response.getAuctionItemId().toString(),AuctionBidItemRequest.builder()
                    .currentBid(bidRequest.getMaxAutoBidAmount())
                    .bidderName(bidRequest.getBidderName())
                    .build());
            final NotificationRequest notificationRequest =
                    NotificationRequest.builder()
                            .bidderName(response.getBidderName())
                            .currentBid(bidRequest.getMaxAutoBidAmount()).build();
            rabbitMQMessageProducer.publish(
                notificationRequest, "internal.exchange", "internal.notification.routing-key"
            );
        }
        else{
            throw new IllegalStateException("Bid amount does not meet");
        }
    }
}
