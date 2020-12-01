package com.repository;

import com.domain.Bookings;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingsRepository extends CrudRepository<Bookings, Long> {
    @Override
    List<Bookings> findAll();

    Bookings findBookingsById(long id);
}
