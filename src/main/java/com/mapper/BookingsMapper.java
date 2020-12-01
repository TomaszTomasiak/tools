package com.mapper;

import com.domain.Bookings;
import com.dto.BookingsDto;
import com.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingsMapper {

    @Autowired
    private BookingsRepository repository;

    public Bookings mapToBookings(final BookingsDto dto) {
        return new Bookings(
                dto.getId(),
                dto.getTool(),
                dto.getUser(),
                dto.getBookedDateFrom(),
                dto.getBookedDateTo());
    }

    public BookingsDto mapToDto(final Bookings bookings) {
        return new BookingsDto(
                bookings.getId(),
                bookings.getTool(),
                bookings.getUser(),
                bookings.getBookedDateFrom(),
                bookings.getBookedDateTo());
    }

    public List<BookingsDto> mapToDtoList(final List<Bookings> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
