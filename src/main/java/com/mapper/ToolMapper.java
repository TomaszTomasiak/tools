package com.mapper;

import com.domain.Tool;
import com.dto.ToolDto;
import com.repository.LocationRepository;
import com.repository.ModelRepository;
import com.repository.ProducerRepository;
import com.repository.ToolsGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToolMapper {

    @Autowired
    private ToolsGroupRepository groupRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ModelRepository modelRepository;

    public Tool mapToTool(final ToolDto dto) {
        Tool toolBean = new Tool();
        toolBean.setId(dto.getId());
        toolBean.setName(dto.getName());
        toolBean.setProducer(producerRepository.findProducerById(dto.getProducerId()));
        toolBean.setModel(modelRepository.findModelById(dto.getModelId()));
        toolBean.setGroup(groupRepository.findToolsGroupById(dto.getGroupId()));
        toolBean.setRentRate(dto.getRentRate());
        toolBean.setLocation(locationRepository.findLocationById(dto.getLocationId()));
        return toolBean;
    }

    public ToolDto mapToToolDto(final Tool tool) {
        ToolDto toolDtoBean = new ToolDto();
        toolDtoBean.setId(tool.getId());
        toolDtoBean.setName(tool.getName());
        toolDtoBean.setProducerId(tool.getProducer().getId());
        toolDtoBean.setModelId(tool.getModel().getId());
        toolDtoBean.setGroupId(tool.getId());
        toolDtoBean.setRentRate(tool.getRentRate());
        toolDtoBean.setLocationId(tool.getLocation().getId());
        return toolDtoBean;
    }

    public List<ToolDto> mapToToolDtoList(final List<Tool> tools) {
        return tools.stream()
                .map(this::mapToToolDto)
                .collect(Collectors.toList());
    }
}
