package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "tool")
    @NotNull
    private Tool tool;

    @ManyToOne
    @JoinColumn(name = "user")
    @NotNull
    private User user;

    @Column(name = "from")
    @NotNull
    private LocalDate bookedDateFrom;

    @Column(name = "to")
    private LocalDate bookedDateTo;
}
