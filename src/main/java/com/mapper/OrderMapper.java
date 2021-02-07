package com.mapper;

import com.domain.Booking;
import com.domain.Cart;
import com.domain.Order;
import com.domain.User;
import com.dto.OrderDto;
import com.repository.CartRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class OrderMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;


    BigDecimal calculateValueForRent(final Booking booking) {
        long daysBetween = DAYS.between(booking.getBookedDateFrom(), booking.getBookedDateTo());
        return BigDecimal.valueOf(daysBetween).multiply(booking.getTool().getRentRate());
    }

    BigDecimal calculateTotalValue(final Order order) {
        if (order.getCart() == null) {
            return BigDecimal.ZERO;
        }
        if (order.getCart().getBookings() == null) {
            return BigDecimal.ZERO;
        }

        return order.getCart().getBookings().stream()
                .map(this::calculateValueForRent)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_DOWN);
    }

    private Cart fetchCartById(final Long id) {
        if (id == null) {
            return null;
        }
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if(cartRepository.findById(id).isPresent()) {
            return cartOptional.get();
        }
        return null;
    }

    private User fetchUserById(final Long id) {
        if (id == null) {
            return null;
        }
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public Order mapToOrder(final OrderDto dto) {
        Order bean = new Order();
        bean.setId(dto.getId());
        bean.setUser(fetchUserById(dto.getUserId()));
        bean.setCart(fetchCartById(dto.getCartId()));
        return new Order();
    }

    public OrderDto mapToDto(final Order order) {
        OrderDto dtoBean = new OrderDto();
        dtoBean.setId(order.getId());
        dtoBean.setUserId(order.getUser().getId());
        dtoBean.setCartId(order.getCart().getId());
        dtoBean.setTotalCost(calculateTotalValue(order));
        return dtoBean;
    }

    public List<OrderDto> mapToDtoList(final List<Order> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
