package com.controller;

import com.dto.BookingDto;
import com.exception.NotFoundException;
import com.mapper.BookingMapper;
import com.service.BookingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {
    @Autowired
    private BookingMapper mapper;

    @Autowired
    private BookingServiceImpl service;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<BookingDto> getAllBookings() {
        log.debug("REST request to get all bookings");
        return mapper.mapToDtoList(service.getAllBookings());
    }

    @GetMapping("/{id}")
    public BookingDto getBooking(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get booking with id: {}", id);
        return mapper.mapToDto(service.getBooking(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto createBooking(@RequestBody BookingDto dto) {
        log.debug("REST request to add new booking: {}", dto);
        service.saveBookings(mapper.mapToBookings(dto));
        return dto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto updateBookingById(@PathVariable("id") long id, @RequestBody BookingDto dto) {
        log.debug("REST request to update booking with id: {}", id);
        return mapper.mapToDto(service.saveBookings(mapper.mapToBookings(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteBookingById(@PathVariable("id") long id) {
        log.debug("REST request to delete booking with id: {}", id);
        service.deleteBooking(id);
    }
}
