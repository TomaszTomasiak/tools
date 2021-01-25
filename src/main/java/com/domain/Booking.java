package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    @ManyToOne
    @JoinColumn(name = "TOOL_ID")
    private Tool tool;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
