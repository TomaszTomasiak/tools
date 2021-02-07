package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    @Builder.Default
    @OneToMany(
            targetEntity = Booking.class,
            mappedBy = "cart",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    private List<Booking> bookings = new ArrayList<>();

    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private Order order;
}
