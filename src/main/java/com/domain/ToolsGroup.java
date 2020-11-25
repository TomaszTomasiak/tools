package com.domain;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tool_group")
public class ToolsGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            targetEntity = Tool.class,
            mappedBy = "groupId",
            fetch = FetchType.LAZY
    )
    List<Tool> toolList = new ArrayList<>();

}
