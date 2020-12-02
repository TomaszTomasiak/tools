package com.service;

import com.domain.Bookings;
import com.domain.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookingsService {
    public List<Bookings> getAllBookings();

    public Optional<Bookings> getBooking(long id);

    public Bookings saveBookings(Bookings bookings);

    public Bookings getBookingById(long id);

    public void deleteBooking(long id);
}
