package com.sevice;

import com.domain.Booking;
import com.domain.Tool;
import com.domain.ToolsGroup;
import com.service.BookingServiceImpl;
import com.service.OrderServiceImpl;
import com.service.ToolServiceImpl;
import com.service.ToolsGroupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTestSuite {

    @Autowired
    private BookingServiceImpl service;

    @Autowired
    private ToolServiceImpl toolService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ToolsGroupServiceImpl groupService;

    @Test
    public void testSaveAndGetAllBookings () {
        //Given
       int bookingListSizeBeforeSavingNewBooking = service.getAllBookings().size();

        ToolsGroup group = ToolsGroup.builder()
                .name("budowlane")
                .build();
        groupService.saveGroup(group);
        long groupId = group.getId();

        Tool tool = Tool.builder()
                .name("betoniarka")
                .group(group)
                .rentRate(BigDecimal.TEN)
                .build();

        toolService.saveTool(tool);
        long toolId = tool.getId();

        Booking booking = Booking.builder()
                .tool(tool)
                .bookedDateFrom(LocalDate.of(2020, 10, 1))
                .bookedDateTo(LocalDate.of(2020, 10, 22))
                .build();

        //When
        service.saveBookings(booking);
        int bookingListSizeAfterSavingNewBooking = service.getAllBookings().size();

        //Then
        assertTrue(bookingListSizeAfterSavingNewBooking > bookingListSizeBeforeSavingNewBooking);
        assertEquals("betoniarka", booking.getTool().getName());
        assertEquals("budowlane", booking.getTool().getGroup().getName());
    }
}
