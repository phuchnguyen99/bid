package com.springpractice.auction;

import com.springpractice.auction.entity.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuctionRepository extends JpaRepository<AuctionItem, Long> {

    @Query(value = "select * from auction_item ai where ai.item_item_id = :itemId", nativeQuery = true)
    Optional<AuctionItem> getAuctionItemByItemId(String itemId);
}
