package com.service;

import com.domain.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> getAllBookings();

    Optional<Booking> getBooking(long id);

    Booking saveBookings(Booking booking);

    Booking getBookingById(long id);

    void deleteBooking(long id);
}
