package com.repository;

import com.domain.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Override
    List<Booking> findAll();

    Booking findBookingsById(Long id);

    @Override
    void deleteById(Long id);
}
