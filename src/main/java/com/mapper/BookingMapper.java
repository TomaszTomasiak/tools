package com.mapper;

import com.domain.Booking;
import com.dto.BookingDto;
import com.repository.OrderRepository;
import com.repository.ToolRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Booking mapToBookings(final BookingDto dto) {
        Booking bookingBean = new Booking();
        bookingBean.setId(dto.getId());
        bookingBean.setBookedDateFrom(dto.getBookedDateFrom());
        bookingBean.setBookedDateTo(dto.getBookedDateTo());
        bookingBean.setTool(toolRepository.findToolById(dto.getToolId()));
        bookingBean.setOrder(orderRepository.findOrderById(dto.getOrderId()));
        return bookingBean;

    }

    public BookingDto mapToDto(final Booking booking) {
        BookingDto dtoBean = new BookingDto();
        dtoBean.setId(booking.getId());
        dtoBean.setBookedDateFrom(booking.getBookedDateFrom());
        dtoBean.setBookedDateTo(booking.getBookedDateTo());
        dtoBean.setToolId(booking.getTool().getId());
        if(booking.getOrder()== null) {
            dtoBean.setOrderId(null);
        }
        else {
            dtoBean.setOrderId(booking.getOrder().getId());
        }
        return dtoBean;
    }

    public List<BookingDto> mapToDtoList(final List<Booking> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
