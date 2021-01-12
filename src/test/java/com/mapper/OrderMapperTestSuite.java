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

    @Test
    public void mapToOrderTest() {
        //Given
        OrderDto orderDto = OrderDto.builder().build();
        //When
        Order order1 = mapper.mapToOrder(orderDto);

        //Then
        assertNotNull(order1);
        assertEquals(0, order1.getId());
    }

    @Test
    public void mapToOrderDtoTest() {
        //Given
        User user = User.builder()
                .id(5L)
                .build();

        Order order = Order.builder()
                .id(7L)
                .user(user)
                .build();

        //When
        OrderDto orderDto = mapper.mapToDto(order);

        //Then
        assertNotNull(orderDto);
        assertEquals(7L, orderDto.getId());
    }

    @Test
    public void mapToOrderDtoListTest() {
        //Given
        User user = User.builder()
                .id(5L)

                .build();

        Order order = Order.builder()
                .id(7L)
                .user(user)
                .build();

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
