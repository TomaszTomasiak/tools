package com.service;

import com.domain.Tool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public interface ToolService {
    List<Tool> getAllTools();

    Optional<Tool> getTool(long id);

    Tool saveTool(Tool tool);

    Tool getToolById(long id);

    void deleteTool(long id);
}
