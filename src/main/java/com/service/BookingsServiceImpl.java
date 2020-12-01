package com.service;

import com.domain.Bookings;
import com.domain.Tool;
import com.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingsServiceImpl implements BookingsService {

    @Autowired
    BookingsRepository repository;


    @Override
    public List<Bookings> getAllBookings() {
        return repository.findAll();
    }

    @Override
    public Optional<Bookings> getBooking(long id) {
        return repository.findById(id);
    }

    @Override
    public Bookings saveBookings(Bookings bookings) {
        return repository.save(bookings);
    }

    @Override
    public Bookings getToolById(long id) {
        return repository.findBookingsById(id);
    }

    @Override
    public void deleteTool(long id) {
        repository.deleteById(id);
    }
}
