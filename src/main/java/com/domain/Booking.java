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
    private long id;

    @Column(name = "BOOKED_TO")
    private LocalDate bookedDateTo;

    @Column(name = "BOOKED_FROM")
    private LocalDate bookedDateFrom;

    @ManyToOne
    @JoinColumn(name = "TOOL_ID")
    private Tool tool;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


}
