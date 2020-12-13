package com.service;

import com.domain.Order;
import com.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Optional<Order> getOrder(long id) {
        return Optional.empty();
    }

    @Override
    public Order saveOrder(Order booking) {
        return null;
    }

    @Override
    public Order getOrderById(long id) {
        return null;
    }

    @Override
    public void deleteOrder(long id) {

    }

    @Override
    public List<Order> getAllOrdersOfUser(User user) {
        return null;
    }
}
