package com.controller;

import com.config.LocalDateSerializer;
import com.dto.BookingDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.resourcesData.BookingDtoCreator;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
@WebMvcTest(BookingController.class)
public class BookingControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingController controller;

    private BookingDto bookingDto;

    @Before
    public void init() {
        bookingDto = BookingDtoCreator.bookingDtoCreator();
        bookingDto.setId(1L);
        bookingDto.setOrderId(1L);
        bookingDto.setToolId(1L);
    }

    @Test
    public void schouldFetchEmptyListOfBookings() throws Exception {
        //Given
        List<BookingDto> bookingDtos = new ArrayList<>();
        when(controller.getAllBookings()).thenReturn(bookingDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/bookings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfBookings() throws Exception {
        //Given
        List<BookingDto> bookingDtos = new ArrayList<>();
        bookingDtos.add(bookingDto);
        when(controller.getAllBookings()).thenReturn(bookingDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/bookings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].orderId", is(1)))
                .andExpect(jsonPath("$[0].bookedDateFrom", is("2020-06-14")))
                .andExpect(jsonPath("$[0].toolId", is(1)));
    }

    @Test
    public void shouldGetBookingWithIndicatedId() throws Exception {
        //Given
        long id = bookingDto.getId();
        when(controller.getBooking(id)).thenReturn(bookingDto);

        //When & Then
        mockMvc.perform(get("/api/v1/bookings/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toolId", is(1)))
                .andExpect(jsonPath("$.orderId", is(1)))
                .andExpect(jsonPath("$.bookedDateTo", is("2020-06-22")));
    }

    @Test
    public void shouldDeleteBooking() throws Exception {
        //Given
        List<BookingDto> bookingDtos = new ArrayList<>();
        bookingDtos.add(bookingDto);
        when(controller.getAllBookings()).thenReturn(bookingDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/bookings/" + bookingDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateBooking() throws Exception {
        //Given

        BookingDto updatedBookingDto = BookingDtoCreator.updatedBookingDtoCreator();
        updatedBookingDto.setId(1L);
        updatedBookingDto.setOrderId(2L);
        updatedBookingDto.setToolId(2L);
        updatedBookingDto.setBookedDateFrom(LocalDate.of(2019, 11, 22));
        when(controller.updateBookingById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(BookingDto.class)))).thenReturn(updatedBookingDto);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.create();
        String jsonContent = gson.toJson(updatedBookingDto);

        //When & Then
        mockMvc.perform(put("/api/v1/bookings/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.orderId", is(2)))
                .andExpect(jsonPath("$.bookedDateFrom", is("2019-11-22")));
    }

    @Test
    public void shouldCreateBooking() throws Exception {


        when(controller.createBooking(ArgumentMatchers.any(BookingDto.class))).thenReturn(bookingDto);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.create();
        String jsonContent = gson.toJson(bookingDto);

        //When & Then
        mockMvc.perform(post("/api/v1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", is(1)))
                .andExpect(jsonPath("$.bookedDateFrom", is("2020-06-14")));
    }

    @Test
    public void shouldNotCreateBookingBecauseToolWithIdAlreadyExists() throws Exception {


        when(controller.createBooking(ArgumentMatchers.any(BookingDto.class))).thenReturn(bookingDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookingDto);


        mockMvc.perform(post("/api/v1/ecommercee/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isNotFound());
    }
}
