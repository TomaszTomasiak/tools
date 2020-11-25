package com.controller;

import com.domain.ToolsGroup;
import com.dto.ToolsGroupDto;
import com.exception.NotFoundException;
import com.mapper.ToolsGroupMapper;
import com.service.ToolsGroupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/groups")
@CrossOrigin("*")
public class ToolsGroupController {

    @Autowired
    private ToolsGroupMapper mapper;

    @Autowired
    private ToolsGroupServiceImpl service;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<ToolsGroupDto> getAllGroups() {
        log.debug("REST request to get all groups");
        return mapper.mapToToolsGroupDtoList(service.getAllGroups());
    }

    @GetMapping("/{id}")
    public ToolsGroupDto getTool(@PathVariable("id") Long id) throws NotFoundException {
        log.debug("REST request to get group with id: {}", id);
        return mapper.mapToToolsGroupDto(service.getGroup(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToolsGroupDto createTool(@RequestBody ToolsGroupDto dto) {
        log.debug("REST request to add new group: {}", dto);
        service.saveGroup(mapper.mapToToolsGroup(dto));
        return dto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToolsGroupDto updateToolById(@PathVariable("id") Long id, @RequestBody ToolsGroupDto dto) {
        log.debug("REST request to update group with id: {}", id);
        return mapper.mapToToolsGroupDto(service.saveGroup(mapper.mapToToolsGroup(dto)));
    }
}
