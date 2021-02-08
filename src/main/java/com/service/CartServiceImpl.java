package com.service;

import com.domain.Cart;
import com.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository repository;

    private final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public List<Cart> getAllCarts() {
        log.debug("Request to get all carts");
        return repository.findAll();
    }

    @Override
    public Optional<Cart> getCart(long id) {
        log.debug("Request to get cart with id: {}", id);
        return repository.findById(id);
    }

    @Override
    public Cart saveCart(Cart cart) {
        log.debug("Request to save cart: {}", cart);
        return repository.save(cart);
    }

    @Override
    public Cart getCartById(long id) {
        log.debug("Request to get cart with id: {}", id);
        return repository.findCartById(id);
    }

    @Override
    public void deleteCart(long id) {
        log.debug("Request to remove cart with id: {}", id);
        repository.deleteById(id);
    }
}
