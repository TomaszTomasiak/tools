package com.service;

import com.domain.Booking;
import com.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository repository;


    @Override
    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    @Override
    public Optional<Booking> getBooking(long id) {
        return repository.findById(id);
    }

    @Override
    public Booking saveBookings(Booking booking) {
        return repository.save(booking);
    }

    @Override
    public Booking getBookingById(long id) {
        return repository.findBookingsById(id);
    }

    @Override
    public void deleteBooking(long id) {
        repository.deleteById(id);
    }
}
