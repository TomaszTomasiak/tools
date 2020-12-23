package com.service;

import com.domain.Booking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {
    List<Booking> getAllBookings();

    Optional<Booking> getBooking(long id);

    Booking saveBookings(Booking booking);

    Booking getBookingById(long id);

    void deleteBooking(long id);
}
