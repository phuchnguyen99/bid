package com.springpractice.bids.service;

import com.springpractice.amqp.RabbitMQMessageProducer;
import com.springpractice.bids.dto.BidRequest;
import com.springpractice.bids.exception.BidErrorCode;
import com.springpractice.bids.exception.BidsException;
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
    public void bidAnAuctionItem(final BidRequest bidRequest) throws BidsException{
        try
        {
            final AuctionItemResponse response =
                    auctionClient.getAuctionItem(bidRequest.getAuctionItemId());
            if(response.getReservePrice() > bidRequest.getMaxAutoBidAmount()){
                throw new BidsException(String.format("The bid is less than reserve price %s",
                        response.getReservePrice()), BidErrorCode.ERROR_BID_RESERVE_PRICE_NOT_MET);
            }
            if(response.getCurrentBid() >= bidRequest.getMaxAutoBidAmount()){
                throw new BidsException(String.format("The bid amount does not meet. " +
                                "Please invest more than $%f.", response.getCurrentBid()),
                                BidErrorCode.ERROR_BID_CURRENT_BID_NOT_MET);
            }
                auctionClient.updateAuctionItem(response.getAuctionItemId().toString(),
                        AuctionBidItemRequest.builder()
                        .currentBid(bidRequest.getMaxAutoBidAmount())
                        .bidderName(bidRequest.getBidderName())
                        .build());
                final NotificationRequest notificationRequest =
                        NotificationRequest.builder()
                                .bidderName(response.getBidderName())
                                .currentBid(bidRequest.getMaxAutoBidAmount()).build();
                rabbitMQMessageProducer.publish(
                    notificationRequest, "internal.exchange",
                        "internal.notification.routing-key"
                );
        }
        catch(final RuntimeException e){
            throw new BidsException("Unable to bid an auction item", BidErrorCode.ERROR_BID_GENERAL);
        }
    }
}
