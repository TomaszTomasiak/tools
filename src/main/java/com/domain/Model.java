package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "producer")
    private Producer producer;

    @Builder.Default
    @OneToMany(
            targetEntity = Tool.class,
            mappedBy = "model",
            fetch = FetchType.LAZY
    )
    private List<Tool> toolList = new ArrayList<>();
}
