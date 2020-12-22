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
public class ToolsGroupDto {
    private long id;
    private String name;

    @Builder.Default
    private List<Tool> toolList = new ArrayList<>();
}
