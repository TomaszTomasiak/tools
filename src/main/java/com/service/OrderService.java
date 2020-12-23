package com.service;

import com.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<Order> getAllOrders();

    Optional<Order> getOrder(long id);

    Order saveOrder(Order booking);

    Order getOrderById(long id);

    void deleteOrder(long id);

    List<Order> getAllOrdersOfUser(long user);

}
