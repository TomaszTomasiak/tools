package com.controller;


import com.dto.OrderDto;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderController controller;

    private OrderDto orderDto;

    @Before
    public void init() {
        orderDto = OrderDto.builder()
                .id(1L)
                .userId(1L)
                .build();
    }

    @Test
    public void schouldFetchEmptyListOfOrders() throws Exception {
        //Given
        List<OrderDto> orderDtos = new ArrayList<>();
        when(controller.getAllOrders()).thenReturn(orderDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfOrders() throws Exception {
        //Given
        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(orderDto);
        when(controller.getAllOrders()).thenReturn(orderDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)));
    }

    @Test
    public void shouldGetOrderWithIndicatedId() throws Exception {
        //Given
        long id = orderDto.getId();
        when(controller.getOrder(id)).thenReturn(orderDto);

        //When & Then
        mockMvc.perform(get("/api/v1/orders/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void shouldDeleteOrder() throws Exception {
        //Given
        List<OrderDto> orderDtos = new ArrayList<>();
        orderDtos.add(orderDto);
        when(controller.getAllOrders()).thenReturn(orderDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/orders/" + orderDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateOrder() throws Exception {
        //Given

        OrderDto updatedDto = new OrderDto();
        updatedDto.setId(1L);
        updatedDto.setUserId(2L);
        when(controller.updateOrderById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(OrderDto.class)))).thenReturn(updatedDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedDto);

        //When & Then
        mockMvc.perform(put("/api/v1/orders/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(2)));
    }

    @Test
    public void shouldCreateOrder() throws Exception {

        when(controller.createOrder(ArgumentMatchers.any(OrderDto.class))).thenReturn(orderDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(orderDto);

        //When & Then
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)));
    }
}
