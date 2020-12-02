package com.service;

import com.domain.ToolsGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ToolsGroupServiceImpl implements ToolsGroupService {


    @Override
    public List<ToolsGroup> getAllGroups() {
        return null;
    }

    @Override
    public Optional<ToolsGroup> getGroup(long id) {
        return Optional.empty();
    }

    @Override
    public ToolsGroup saveGroup(ToolsGroup group) {
        return null;
    }

    @Override
    public ToolsGroup getGroupById(long id) {
        return null;
    }
}
