package com.domain;

import lombok.*;

import javax.persistence.*;
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

    @Column(name = "producer")
    private String producer;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name="groupId")
    private ToolsGroup groupId;

//    private boolean available;

    @Builder.Default
    @OneToMany(
            targetEntity = Booking.class,
            mappedBy = "tool",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Booking> toolsBookings = new ArrayList<>();
}
