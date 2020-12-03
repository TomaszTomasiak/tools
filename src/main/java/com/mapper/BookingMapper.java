package com.mapper;

import com.domain.Booking;
import com.dto.BookingDto;
import com.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    @Autowired
    private BookingRepository repository;

    public Booking mapToBookings(final BookingDto dto) {
        return new Booking(
                dto.getId(),
                dto.getTool(),
                dto.getUser(),
                dto.getBookedDateFrom(),
                dto.getBookedDateTo());
    }

    public BookingDto mapToDto(final Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getTool(),
                booking.getUser(),
                booking.getBookedDateFrom(),
                booking.getBookedDateTo());
    }

    public List<BookingDto> mapToDtoList(final List<Booking> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
