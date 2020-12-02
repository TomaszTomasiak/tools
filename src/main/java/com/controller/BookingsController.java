package com.controller;

import com.dto.BookingsDto;
import com.exception.NotFoundException;
import com.mapper.BookingsMapper;
import com.service.BookingsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingsController {
    @Autowired
    private BookingsMapper mapper;

    @Autowired
    private BookingsServiceImpl service;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<BookingsDto> getAllBookings() {
        log.debug("REST request to get all bookings");
        return mapper.mapToDtoList(service.getAllBookings());
    }

    @GetMapping("/{id}")
    public BookingsDto getBooking(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get booking with id: {}", id);
        return mapper.mapToDto(service.getBooking(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookingsDto createBooking(@RequestBody BookingsDto dto) {
        log.debug("REST request to add new booking: {}", dto);
        service.saveBookings(mapper.mapToBookings(dto));
        return dto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookingsDto updateBookingById(@PathVariable("id") long id, @RequestBody BookingsDto dto) {
        log.debug("REST request to update booking with id: {}", id);
        return mapper.mapToDto(service.saveBookings(mapper.mapToBookings(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteBookingById(@PathVariable("id") long id) {
        log.debug("REST request to delete booking with id: {}", id);
        service.deleteBooking(id);
    }
}
