package com.service;

import com.domain.ToolsGroup;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public interface ToolsGroupService {

    public List<ToolsGroup> getAllGroups();

    public Optional<ToolsGroup> getGroup(Long id);

    public ToolsGroup saveGroup(ToolsGroup group);

    public ToolsGroup getGroupById(Long id);

}
