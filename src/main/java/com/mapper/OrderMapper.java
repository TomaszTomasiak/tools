package com.mapper;

import com.domain.Booking;
import com.domain.Order;
import com.dto.OrderDto;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class OrderMapper {

    @Autowired
    UserRepository userRepository;

    BigDecimal calculateValueForRent(final Booking booking) {
        long daysBetween = DAYS.between(booking.getBookedDateFrom(), booking.getBookedDateTo());
        return BigDecimal.valueOf(daysBetween).multiply(booking.getTool().getRentRate());
    }

    BigDecimal calculateTotalValue(final Order order) {
        if (order.getBookings().size() == 0) {
            return BigDecimal.ZERO;
        }
        if (order.getBookings() == null) {
            return BigDecimal.ZERO;
        }

        return order.getBookings().stream()
                .map(this::calculateValueForRent)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_DOWN);
    }

    public Order mapToOrder(final OrderDto dto) {
        Order bean = new Order();
        bean.setId(dto.getId());
        bean.setUser(userRepository.findUserById(dto.getUserId()));
        bean.setBookings(dto.getBookings());
        return new Order();
    }

    public OrderDto mapToDto(final Order order) {
        OrderDto dtoBean = new OrderDto();
        dtoBean.setId(order.getId());
        dtoBean.setUserId(order.getUser().getId());
        dtoBean.setBookings(order.getBookings());
        dtoBean.setTotalCost(calculateTotalValue(order));
        return dtoBean;
    }

    public List<OrderDto> mapToDtoList(final List<Order> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
