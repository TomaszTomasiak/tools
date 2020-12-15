package com.resourcesData;

import com.dto.BookingDto;

import java.time.LocalDate;

public class BookingDtoCreator {

    private static final long ID = 1L;
    private static final long TOOL_ID = 10L;
    private static final long ORDER_ID = 28L;
    private static final LocalDate BOOKED_DATE_FROM = LocalDate.of(2020, 06, 14);
    private static final LocalDate BOOKED_DATE_TO = LocalDate.of(2020, 06, 22);

    private static final long ID_UPDATED = 1L;
    private static final long TOOL_ID_UPDATED = 19L;
    private static final long ORDER_ID_UPDATED = 30L;
    private static final LocalDate BOOKED_DATE_FROM_UPDATED = LocalDate.of(2020, 03, 30);
    private static final LocalDate BOOKED_DATE_TO_UPDATED = LocalDate.of(2020, 04, 12);

    public static BookingDto bookingDtoCreator() {
        return BookingDto.builder()
                .id(ID)
                .toolId(TOOL_ID)
                .orderId(ORDER_ID)
                .bookedDateFrom(BOOKED_DATE_FROM)
                .bookedDateTo(BOOKED_DATE_TO)
                .build();

    }

    public static BookingDto updatedBookingDtoCreator() {
        return BookingDto.builder()
                .id(ID_UPDATED)
                .toolId(TOOL_ID_UPDATED)
                .orderId(ORDER_ID_UPDATED)
                .bookedDateFrom(BOOKED_DATE_FROM_UPDATED)
                .bookedDateTo(BOOKED_DATE_TO_UPDATED)
                .build();
    }
}
