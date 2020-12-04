package com.resourcesData;

import com.dto.BookingDto;

import java.time.LocalDate;

public class BookingDtoCreator {

    public static final long ID = 5L;
    public static final long TOOL_ID = 1L;
    public static final long USER_ID = 1L;
    public static final LocalDate BOOKED_DATE_FROM = LocalDate.of(2020, 06, 14);
    public static final LocalDate BOOKED_DATE_TO = LocalDate.of(2020, 06, 22);

    public static final long ID_UPDATED = 5L;
    public static final long TOOL_ID_UPDATED = 2L;
    public static final long USER_ID_UPDATED = 2L;
    public static final LocalDate BOOKED_DATE_FROM_UPDATED = LocalDate.of(2020, 03, 30);
    public static final LocalDate BOOKED_DATE_TO_UPDATED = LocalDate.of(2020, 04, 12);

    public static BookingDto bookingDtoCreator() {
        return BookingDto.builder()
                //.id(ID)
                .toolId(TOOL_ID)
                .userId(USER_ID)
                .bookedDateFrom(BOOKED_DATE_FROM)
                .bookedDateFrom(BOOKED_DATE_TO)
                .build();
    }

    public static BookingDto updatedBookingDtoCreator() {
        return BookingDto.builder()
                //.id(ID_UPDATED)
                .toolId(TOOL_ID_UPDATED)
                .userId(USER_ID_UPDATED)
                .bookedDateFrom(BOOKED_DATE_FROM_UPDATED)
                .bookedDateFrom(BOOKED_DATE_TO_UPDATED)
                .build();
    }
}
