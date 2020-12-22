package com.mapper;

import com.domain.ToolsGroup;
import com.dto.ToolsGroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToolsGroupMapper {

    public ToolsGroup mapToToolsGroup(final ToolsGroupDto dto) {
        return new ToolsGroup(dto.getId(), dto.getName(), dto.getToolList());
    }

    public ToolsGroupDto mapToToolsGroupDto(final ToolsGroup group) {
        return new ToolsGroupDto(group.getId(), group.getName(), group.getToolsList());
    }

    public List<ToolsGroupDto> mapToToolsGroupDtoList(final List<ToolsGroup> list){
        return list.stream()
                .map(this::mapToToolsGroupDto)
                .collect(Collectors.toList());
    }
}
