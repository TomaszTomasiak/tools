package com.mapper;

import com.domain.Booking;
import com.dto.BookingDto;
import com.repository.ToolRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private UserRepository userRepository;


    public Booking mapToBookings(final BookingDto dto) {
        return new Booking(
                dto.getId(),
                dto.getBookedDateFrom(),
                dto.getBookedDateTo(),
                toolRepository.findToolById(dto.getToolId()),
                userRepository.findUserById(dto.getUserId()));
    }

    public BookingDto mapToDto(final Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getBookedDateFrom(),
                booking.getBookedDateTo(),
                booking.getTool().getId(),
                booking.getUser().getId()
                );
    }

    public List<BookingDto> mapToDtoList(final List<Booking> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
