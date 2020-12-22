package com.dto;

import com.domain.Tool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolsGroupDto {
    private long id;
    private String name;
    private List<Tool> toolList;
}
