package com.dao;
import com.domain.Order;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTestSuite {


    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testOrderDaoSave() {

        Order order = new Order();
        orderRepository.save(order);
        long id = order.getId();

        assertEquals(0, order.getBookings().size());
        assertNotEquals(0, id);
    }
}
