package com.mapper;

import com.domain.Order;
import com.domain.User;
import com.dto.OrderDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestSuite {

    @Autowired
    private OrderMapper mapper;

    private Order order;
    private OrderDto orderDto = OrderDto.builder()
            .id(8L)
            .userId(66L)
            .build();

    @Before
    public void init() {
        User user = User.builder()
                .id(5L)
                .build();

        order = Order.builder()
                .id(7L)
                .user(user)
                .build();
    }

    @Test
    public void mapToOrderTest() {
        //Given

        //When
        Order order1 = mapper.mapToOrder(orderDto);

        //Then
        assertNotNull(order1);
        assertEquals(8L, order1.getId());
        assertEquals(orderDto.getUserId(), order1.getUser().getId());
    }

    @Test
    public void mapToOrderDtoTest() {
        //Given

        //When
        OrderDto orderDto = mapper.mapToDto(order);

        //Then
        assertNotNull(orderDto);
        assertEquals(7L, orderDto.getId());
    }

    @Test
    public void mapToOrderDtoListTest() {
        //Given
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        //When
        List<OrderDto> orderDtos = mapper.mapToDtoList(orders);

        //Then
        assertNotNull(orderDtos);
        assertEquals(1, orderDtos.size());
        assertEquals(order.getUser().getId(), orderDtos.get(0).getUserId());
    }

    @Test
    public void calculateTotalValueTest(){


    }
}
