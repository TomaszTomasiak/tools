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

    @Column(name = "producer")
    private String producer; // change to Producer.class

    @Column(name = "model")
    private String model;

    @Column(name = "rate")
    private BigDecimal rentRate;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private ToolsGroup group;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;

}
