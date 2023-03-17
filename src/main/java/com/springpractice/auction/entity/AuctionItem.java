package com.springpractice.auction.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "auction_item")
public class AuctionItem {
    @Id
    @SequenceGenerator(
            name = "auction_item_id_sequence",
            sequenceName = "auction_item_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "auction_item_id_sequence"
    )
    private Long auctionId;
    private double currentBid;

    private String bidderName;
    private double reservePrice;

    @OneToOne(cascade = CascadeType.ALL)
    private Item item;
}
