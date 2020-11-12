package com.service;

import com.domain.Tool;
import com.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository repository;


    @Override
    public List<Tool> getAllTools() {
        return repository.findAll();
    }

    @Override
    public Optional<Tool> getTool(Long id) {
        return repository.findById(id);
    }

    @Override
    public Tool saveTool(Tool tool) {
        return repository.save(tool);
    }

    @Override
    public Tool getToolById(Long id) {
        return repository.findToolById(id);
    }

    @Override
    public void deleteTool(Long id) {
        repository.deleteById(id);
    }
}
