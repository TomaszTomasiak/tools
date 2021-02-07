package com.mapper;

import com.domain.Cart;
import com.dto.CartDto;
import com.repository.CartRepository;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Cart mapToCart(final CartDto dto) {
        Cart cartBean = new Cart();
        cartBean.setId(dto.getId());
        cartBean.setBookings(dto.getBookings());
        cartBean.setUser(userRepository.findUserById(dto.getUserId()));
        cartBean.setOrder(orderRepository.findOrderById(dto.getOrderId()));
        return cartBean;
    }

    public CartDto mapToCartDto(final Cart cart) {

        CartDto cartDto = new CartDto();
        if (cart.getUser() == null) {
            cartDto.setUserId(null);
        } else {
            cartDto.setUserId(cart.getUser().getId());
        }

        if (cart.getOrder() == null) {
            cartDto.setOrderId(null);
        } else {
            cartDto.setOrderId(cart.getOrder().getId());
        }
        cartDto.setId(cart.getId());

        return cartDto;
    }

    public List<CartDto> mapToDtoList (final List<Cart> carts) {
        return  carts.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}
