package com.service;

import com.domain.ToolsGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface ToolsGroupService {

    List<ToolsGroup> getAllGroups();

    Optional<ToolsGroup> getGroup(long id);

    ToolsGroup saveGroup(ToolsGroup group);

    ToolsGroup getGroupById(long id);

}
