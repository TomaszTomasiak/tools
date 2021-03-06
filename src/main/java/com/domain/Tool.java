package com.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tools")
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rate")
    private BigDecimal rentRate;

    @ManyToOne
    @JoinColumn(name = "producer")
    private Producer producer;

    @ManyToOne
    @JoinColumn(name = "model")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private ToolsGroup group;


    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;

    @Builder.Default
    @OneToMany(
            targetEntity = Booking.class,
            mappedBy = "tool",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    private List<Booking> bookings = new ArrayList<>();

}
