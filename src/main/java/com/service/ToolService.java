package com.service;

import com.domain.Tool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public interface ToolService {
    public List<Tool> getAllTools();

    public Optional<Tool> getTool(long id);

    public Tool saveTool(Tool tool);

    public Tool getToolById(long id);

    public void deleteTool(long id);
}
