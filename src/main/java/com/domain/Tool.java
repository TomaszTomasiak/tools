package com.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tools")
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @OneToMany(
            targetEntity =  Bookings.class,
            mappedBy = "TOOL_ID",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Bookings> toolsBookings = new ArrayList<>();
}
