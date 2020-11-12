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
    private Long id;
    private String name;
    List<Tool> toolList;
}
