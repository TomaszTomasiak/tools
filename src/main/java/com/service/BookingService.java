package com.service;

import com.domain.Booking;

import java.util.List;
import java.util.Optional;


public interface BookingService {
    public List<Booking> getAllBookings();

    public Optional<Booking> getBooking(long id);

    public Booking saveBookings(Booking booking);

    public Booking getBookingById(long id);

    public void deleteBooking(long id);
}
