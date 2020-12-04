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
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "tool")
    private Tool tool;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "from")
    private LocalDate bookedDateFrom;

    @Column(name = "to")
    private LocalDate bookedDateTo;
}
