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

    private User user;
    private Order order;
    private OrderDto dto;

    @Before
    public void init() {
        user = User.builder()
                .id(5L)
                .build();

        order = Order.builder()
                .id(7L)
                .user(user)
                .build();

        dto = OrderDto.builder()
                .id(8L)
                .userId(66L)
                .build();
    }

    @Test
    public void mapToOrder() {
        //Given

        //When
        Order theOrder = mapper.mapToOrder(dto);

        //Then
        assertNotNull(order);
        assertEquals(8L, theOrder.getId());
        assertEquals(order.getUser().getId(), theOrder.getUser().getId());
    }

    @Test
    public void mapToOrderDto() {
        //Given

        //When
        OrderDto orderDto = mapper.mapToDto(order);

        //Then
        assertNotNull(orderDto);
        assertEquals(7L, orderDto.getId());
    }

    @Test
    public void mapToOrderDtoList() {
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
}
