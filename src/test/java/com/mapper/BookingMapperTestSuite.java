package com.mapper;

import com.domain.*;
import com.dto.BookingDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingMapperTestSuite {

    @Autowired
    private BookingMapper mapper;

    private User user = User.builder()
            .id(22L)
            .build();

    private Location location = Location.builder()
            .id(44L)
            .build();


    private Tool tool = Tool.builder()
            .id(5L)
            .build();

    private Order order = Order.builder()
            .id(99L)
            .user(user)
            .build();



    private Booking booking = Booking.builder()
            .id(6L)
            .bookedDateFrom(LocalDate.of(2020, 10, 14))
            .bookedDateTo(LocalDate.of(2020, 10, 25))
            .tool(tool)
//            .order(order)
            .build();

    private BookingDto dto = BookingDto.builder()
            .id(13L)
            .bookedDateFrom(LocalDate.of(2020, 12, 22))
            .bookedDateTo(LocalDate.of(2021, 1, 8))
            .toolId(5L)
            .orderId(2L)
            .build();

    @Test
    public void mapToBooking() {
        //Given

        //When
        Booking booking1 = mapper.mapToBookings(dto);


        //Then
        assertNotNull(booking1);

        assertEquals(13L, booking1.getId());
        assertEquals(LocalDate.of(2021, 1, 8), booking1.getBookedDateTo());
    }

    @Test
    public void mapToBookingDto() {
        //Given

        //When
        BookingDto bookingDto = mapper.mapToDto(booking);

        //Then
        assertNotNull(bookingDto);
        assertEquals(java.util.Optional.of(6L), java.util.Optional.ofNullable(bookingDto.getId()));
        assertEquals(LocalDate.of(2020, 10, 14), bookingDto.getBookedDateFrom());
        assertEquals(java.util.Optional.of(99L), java.util.Optional.ofNullable(bookingDto.getOrderId()));

    }

    @Test
    public void mapToBookingDtoList() {
        //Given

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        //When
        List<BookingDto> locationDtoList = mapper.mapToDtoList(bookings);

        //Then
        assertNotNull(locationDtoList);
        assertEquals(1, locationDtoList.size());
        assertEquals(java.util.Optional.of(6L), java.util.Optional.ofNullable(locationDtoList.get(0).getId()));
    }
}
