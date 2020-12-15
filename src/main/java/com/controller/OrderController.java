package com.controller;

import com.dto.OrderDto;
import com.exception.NotFoundException;
import com.mapper.OrderMapper;
import com.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private OrderServiceImpl service;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<OrderDto> getAllOrders() {
        log.debug("REST request to get all orders");
        return mapper.mapToDtoList(service.getAllOrders());
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get order with id: {}", id);
        return mapper.mapToDto(service.getOrder(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto dto) {
        log.debug("REST request to add new order: {}", dto);
        service.saveOrder(mapper.mapToOrder(dto));
        return dto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrderById(@PathVariable("id") long id, @RequestBody OrderDto dto) {
        log.debug("REST request to update order with id: {}", id);
        return mapper.mapToDto(service.saveOrder(mapper.mapToOrder(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable("id") long id) {
        log.debug("REST request to delete order with id: {}", id);
        service.deleteOrder(id);
    }


}
