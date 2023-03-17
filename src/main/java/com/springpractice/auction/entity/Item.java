package com.springpractice.auction.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Table(name = "item")
public class Item {
    @Id
    private String itemId;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private AuctionItem auctionItem;
}
