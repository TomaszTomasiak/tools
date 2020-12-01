package com.repository;

import com.domain.Tool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public interface ToolRepository extends CrudRepository<Tool, Long> {
//    List<Tool> findToolsByProducer(String producer);
//    List<Tool> findToolsByToolsGroup(Long groupId);


    @Override
    List<Tool> findAll();

    Tool findToolById(long id);
}
