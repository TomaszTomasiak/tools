package com.mapper;

import com.domain.Tool;
import com.dto.ToolDto;
import com.repository.ToolsGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToolMapper {

    @Autowired
    private ToolsGroupRepository groupRepository;

    public Tool mapToTool(final ToolDto dto) {
        Tool toolBean = new Tool();
        toolBean.setId(dto.getId());
        toolBean.setName(dto.getName());
        toolBean.setProducer(dto.getProducer());
        toolBean.setModel(dto.getModel());
        toolBean.setGroupId(groupRepository.findByToolsGroupById(dto.getId()));
        return toolBean;
    }

    public ToolDto mapToToolDto(final Tool tool) {
        ToolDto toolDtoBean = new ToolDto();
        toolDtoBean.setId(tool.getId());
        toolDtoBean.setName(tool.getName());
        toolDtoBean.setProducer(tool.getProducer());
        toolDtoBean.setModel(tool.getModel());
        toolDtoBean.setGroupId(tool.getId());
        return toolDtoBean;
    }

    public List<ToolDto> mapToToolDtoList(final List<Tool> tools) {
        return tools.stream()
                .map(t -> mapToToolDto(t))
                .collect(Collectors.toList());
    }
}
