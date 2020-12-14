package com.mapper;

import com.domain.Order;
import com.dto.OrderDto;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    UserRepository userRepository;

    public Order mapToOrder(final OrderDto dto) {
        Order bean = new Order();
        bean.setId(dto.getId());
        bean.setUser(userRepository.findUserById(dto.getUserId()));
        return new Order();
    }

    public OrderDto mapToDto(final Order order) {
        OrderDto dtoBean = new OrderDto(        );
        dtoBean.setId(order.getId());
        dtoBean.setUserId(order.getUser().getId());
        return dtoBean;
    }

    public List<OrderDto> mapToDtoList(final List<Order> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
