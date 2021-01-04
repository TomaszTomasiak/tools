package com.dto;

import com.domain.Tool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolsGroupDto {
    private long id;
    private String name;
    private List<Tool> toolList;
}
