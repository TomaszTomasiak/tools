package com.service;

import com.domain.Booking;
import com.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository repository;

    @Override
    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    @Override
    public Optional<Booking> getBooking(final long id) {
        return repository.findById(id);
    }

    @Override
    public Booking saveBookings(final Booking booking) {
        return repository.save(booking);
    }

    @Override
    public Booking getBookingById(final long id) {
        return repository.findBookingsById(id);
    }

    @Override
    public void deleteBooking(final long id) {
        repository.deleteById(id);
    }
}
