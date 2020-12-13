package com.repository;

import com.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Override
    List<Order> findAll();

    Order findOrderById(Long id);
}
