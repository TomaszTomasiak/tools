package com.service;

import com.domain.Order;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository repository;

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Optional<Order> getOrder(long id) {
        return repository.findById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public Order getOrderById(long id) {
        return repository.findOrderById(id);
    }

    @Override
    public void deleteOrder(long id) {
    repository.deleteById(id);
    }

    @Override
    public List<Order> getAllOrdersOfUser(long userId) {
        return getAllOrders().stream()
                .filter(o -> o.getUser().getId() == userId)
                .collect(Collectors.toList());
    }
}
