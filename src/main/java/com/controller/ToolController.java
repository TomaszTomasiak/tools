package com.controller;

import com.dto.ToolDto;
import com.exception.NotFoundException;
import com.mapper.ToolMapper;
import com.service.ToolServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tools")
@CrossOrigin("*")
public class ToolController {

    @Autowired
    private ToolMapper mapper;

    @Autowired
    private ToolServiceImpl service;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<ToolDto> getAllTools() {
        log.debug("REST request to get all tools");
        return mapper.mapToToolDtoList(service.getAllTools());
    }

    @GetMapping("/{id}")
    public ToolDto getTool(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get tool with id: {}", id);
        return mapper.mapToToolDto(service.getTool(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToolDto createTool(@RequestBody ToolDto toolDto) {
        log.debug("REST request to add new tool: {}", toolDto);
        service.saveTool(mapper.mapToTool(toolDto));
        return toolDto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToolDto updateToolById(@PathVariable("id") long id, @RequestBody ToolDto toolDto) {
        log.debug("REST request to update tool with id: {}", id);
        return mapper.mapToToolDto(service.saveTool(mapper.mapToTool(toolDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteToolById(@PathVariable("id") long id) {
        log.debug("REST request to delete tool with id: {}", id);
        service.deleteTool(id);
    }
}
