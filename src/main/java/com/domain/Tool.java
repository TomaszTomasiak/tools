package com.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tools")
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "producer")
    private String producer;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name="groupId")
    private ToolsGroup groupId;

    private boolean available;







}
