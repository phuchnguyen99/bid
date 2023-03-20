package com.springpractice.auction.service;


import com.springpractice.auction.AuctionRepository;
import com.springpractice.auction.dto.AuctionItemRequest;
import com.springpractice.auction.entity.AuctionItem;
import com.springpractice.client.auction.AuctionBidItemRequest;
import com.springpractice.client.auction.AuctionItemResponse;
import com.springpractice.client.auction.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuctionService {
    private final AuctionRepository auctionRepository;
    public Long addAuctionItem(AuctionItemRequest auctionItemRequest) {
      Item item = auctionItemRequest.getItem();
      Optional<AuctionItem> auctionItem = auctionRepository.getAuctionItemByItemId(item.getItemId());
      if(auctionItem.isPresent()){
          throw new IllegalStateException(String.format("Item id %s already exists", item.getItemId()));
      }
      AuctionItem savedAuctionItem = AuctionItem.builder()
              .reservePrice(auctionItemRequest.getReservePrice())
              .item(com.springpractice.auction.entity.Item.builder().itemId(item.getItemId())
                      .description(item.getDescription()).build())
              .build();
      auctionRepository.saveAndFlush(savedAuctionItem);
      return savedAuctionItem.getAuctionId();
    }

    public List<AuctionItemResponse> getAllAuctionItems() {
        List<AuctionItem> auctionItems = auctionRepository.findAll();
        return auctionItems.stream().map(this::convertAuctionItemResponse)
                .collect(Collectors.toList());
    }

    private AuctionItemResponse convertAuctionItemResponse(final AuctionItem auctionItem){
        return AuctionItemResponse.builder()
                    .auctionItemId(auctionItem.getAuctionId())
                    .reservePrice(auctionItem.getReservePrice())
                    .bidderName(auctionItem.getBidderName())
                    .currentBid(auctionItem.getCurrentBid())
                    .item(Item.builder()
                            .itemId(auctionItem.getItem().getItemId())
                            .description(auctionItem.getItem().getDescription())
                            .build())
                    .build();
    }

    public AuctionItemResponse getAuctionItem(String auctionItemId) {
        final Optional<AuctionItem> auctionItem = auctionRepository.findById(Long.valueOf(auctionItemId));
        if(!auctionItem.isPresent()){
            throw new IllegalStateException("The auction item not found");
        }
        return convertAuctionItemResponse(auctionItem.get());
    }

    public void updateBidOnAuctionItem(String auctionItem, AuctionBidItemRequest auctionBidItemRequest) {
        final Optional<AuctionItem> auctionItemOptional = auctionRepository.findById(Long.valueOf(auctionItem));
        if(!auctionItemOptional.isPresent()){
            throw new IllegalStateException("The auction item not found");
        }
        final AuctionItem savedAuctionItem = auctionItemOptional.get();
        savedAuctionItem.setBidderName(auctionBidItemRequest.getBidderName());
        savedAuctionItem.setCurrentBid(auctionBidItemRequest.getCurrentBid());
        log.info("Saving auction item {}", savedAuctionItem);
        auctionRepository.save(savedAuctionItem);
    }
}
