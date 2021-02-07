package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;
}
