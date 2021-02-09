package com.repository;

import com.domain.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {

    @Override
    List<Model> findAll();

    Model findModelById(Long id);
}
