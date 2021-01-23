package com.service;

import com.domain.Tool;

import java.util.List;
import java.util.Optional;

public interface ToolService {
    List<Tool> getAllTools();

    Optional<Tool> getTool(long id);

    Tool saveTool(Tool tool);

    Tool getToolById(long id);

    void deleteTool(long id);
}
