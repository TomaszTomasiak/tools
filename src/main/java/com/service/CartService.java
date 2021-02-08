package com.service;

import com.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> getAllCarts();

    Optional<Cart> getCart(long id);

    Cart saveCart(Cart cart);

    Cart getCartById(long id);

    void deleteCart(long id);
}
