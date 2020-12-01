package com.repository;

import com.domain.Bookings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookingsRepository extends CrudRepository<Bookings, Long> {
    @Override
    List<Bookings> findAll();

    Bookings findBookingsById(long id);
}
