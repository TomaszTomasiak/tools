package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BOOKINGS")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "BOOKING_ID")
    private long id;

    @Column(name = "BOOKED_FROM")
    private LocalDate bookedDateFrom;

    @Column(name = "BOOKED_TO")
    private LocalDate bookedDateTo;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Tool tool;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;
}
