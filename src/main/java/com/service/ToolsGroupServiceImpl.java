package com.service;

import com.domain.ToolsGroup;
import com.repository.ToolsGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ToolsGroupServiceImpl implements ToolsGroupService {

    @Autowired
    private ToolsGroupRepository repository;

    @Override
    public List<ToolsGroup> getAllGroups() {
        return repository.findAll();
    }

    @Override
    public Optional<ToolsGroup> getGroup(long id) {
        return repository.findById(id);
    }

    @Override
    public ToolsGroup saveGroup(ToolsGroup group) {
        return repository.save(group);
    }

    @Override
    public ToolsGroup getGroupById(long id) {
        return repository.findToolsGroupById(id);
    }
}
