package com.mapper;

import com.domain.Booking;
import com.dto.BookingDto;
import com.repository.CartRepository;
import com.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private CartRepository cartRepository;

    public Booking mapToBooking(final BookingDto dto) {
        Booking bookingBean = new Booking();
        bookingBean.setId(dto.getId());
        bookingBean.setBookedDateFrom(dto.getBookedDateFrom());
        bookingBean.setBookedDateTo(dto.getBookedDateTo());
        bookingBean.setTool(toolRepository.findToolById(dto.getToolId()));
        bookingBean.setCart(cartRepository.findById(dto.getCartId()).orElse(null));
        return bookingBean;
    }

    public BookingDto mapToDto(final Booking booking) {
        BookingDto dtoBean = new BookingDto();
        dtoBean.setId(booking.getId());
        dtoBean.setBookedDateFrom(booking.getBookedDateFrom());
        dtoBean.setBookedDateTo(booking.getBookedDateTo());
        dtoBean.setToolId(booking.getTool().getId());
        if (booking.getCart() == null) {
            dtoBean.setCartId(0);
        } else {
            dtoBean.setCartId(booking.getCart().getId());
        }
        return dtoBean;
    }

    public List<BookingDto> mapToDtoList(final List<Booking> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
