package com.mapper;

import com.domain.Order;
import com.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order mapToOrder(final OrderDto dto) {
        return new Order();
    }

    public OrderDto mapToDto(final Order booking) {
        return new OrderDto(        );
    }

    public List<OrderDto> mapToDtoList(final List<Order> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
