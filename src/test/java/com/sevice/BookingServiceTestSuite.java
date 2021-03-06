package com.sevice;

import com.domain.*;
import com.exception.EmailExistsException;
import com.exception.NotFoundException;
import com.exception.PeselExistException;
import com.repository.BookingRepository;
import com.resourcesData.ToolCreator;
import com.service.*;
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
    private BookingServiceImpl bookingService;

    @Autowired
    private ToolServiceImpl toolService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ToolsGroupServiceImpl groupService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private LocationServiceImpl locationService;




    @Test
    public void testSaveAndGetAllBookings () {
        //Given
       int bookingListSizeBeforeSavingNewBooking = bookingService.getAllBookings().size();

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
        bookingService.saveBookings(booking);
        int bookingListSizeAfterSavingNewBooking = bookingService.getAllBookings().size();

        //Then
        assertTrue(bookingListSizeAfterSavingNewBooking > bookingListSizeBeforeSavingNewBooking);
        assertEquals("betoniarka", booking.getTool().getName());
        assertEquals("budowlane", booking.getTool().getGroup().getName());
    }

    @Test
    public void testRemoveBooking () throws EmailExistsException, PeselExistException {
        //Given
        int bookingListSizeBeforeSavingNewBooking = bookingService.getAllBookings().size();
        ToolsGroup group = ToolsGroup.builder()
                .name("budowlane")
                .build();
        groupService.saveGroup(group);

        Location location = Location.builder()
                .country("Poland")
                .city("Warsaw")
                .zipCode("00-950")
                .address("Woronicza 17")
                .email("email111111@test.pl")
                .phone("666111333")
                .build();
        locationService.saveLocation(location);

        Tool tool = ToolCreator.toolCreator();
        toolService.saveTool(tool);

        User user = User.builder()
                .name("Zbigniew")
                .surname("Kos")
                .email("test1211122@mail.com")
                .password("password2222")
                .pesel("99887744")
                .phone("222222222")
                .build();

        userService.save(user);

        Order order = Order.builder()
                .user(user)
                .build();

        orderService.saveOrder(order);

        Booking theBooking = Booking.builder()
                .tool(tool)
                .bookedDateFrom(LocalDate.of(2020, 10, 1))
                .bookedDateTo(LocalDate.of(2020, 10, 22))
                .build();

        //When
        bookingService.saveBookings(theBooking);
        long theBookingId = theBooking.getId();

        int bookingListSizeAfterSaveBooking = bookingService.getAllBookings().size();

        bookingService.deleteBooking(theBookingId);

        int bookingListSizeAfterDeleteBooking = bookingService.getAllBookings().size();
        //Then

       assertEquals(bookingListSizeBeforeSavingNewBooking + 1 , bookingListSizeAfterSaveBooking);
       assertEquals(bookingListSizeBeforeSavingNewBooking, bookingListSizeAfterDeleteBooking);
       assertFalse(bookingService.getBooking(theBookingId).isPresent());
    }

    @Test
    public void testFindExistingBookingById() throws NotFoundException {
        //Given

        ToolsGroup group = ToolsGroup.builder()
                .name("budowlane")
                .build();
        groupService.saveGroup(group);

        Tool tool = Tool.builder()
                .name("betoniarka")
                .group(group)
                .rentRate(BigDecimal.TEN)
                .build();

        toolService.saveTool(tool);

        Booking theBooking = Booking.builder()
                .tool(tool)
                .bookedDateFrom(LocalDate.of(2020, 10, 1))
                .bookedDateTo(LocalDate.of(2020, 10, 22))
                .build();

        //When
        bookingService.saveBookings(theBooking);
        long bookingId = theBooking.getId();

        Booking newBooking = bookingService.getBooking(bookingId).orElseThrow(NotFoundException::new);

        //Then
        assertTrue(bookingService.getBooking(bookingId).isPresent());
        assertEquals(bookingId, theBooking.getId());
        assertEquals(theBooking.getBookedDateFrom(), newBooking.getBookedDateFrom());
    }

    @Test(expected = NotFoundException.class)
    public void findNotExistingBooking() throws NotFoundException {
        //Given
        //When
        Booking booking = bookingService.getBooking(55555).orElseThrow(NotFoundException::new);

        //Then
        assertFalse(bookingService.getBooking(55555).isPresent());
    }
}
