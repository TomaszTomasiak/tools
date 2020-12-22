package com.repository;

import com.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    @Override
    List<Location> findAll();

    Location findLocationById(Long id);
}
