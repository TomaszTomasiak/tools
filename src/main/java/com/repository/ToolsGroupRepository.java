package com.repository;

import com.domain.ToolsGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ToolsGroupRepository extends CrudRepository<ToolsGroup, Long> {

    ToolsGroup findByToolsGroupById(Long id);

    @Override
    List<ToolsGroup> findAll();
}

