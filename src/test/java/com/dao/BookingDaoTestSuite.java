package com.dao;

import com.domain.*;
import com.repository.*;
import com.resourcesData.ToolCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingDaoTestSuite {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ToolsGroupRepository groupRepository;

    @Autowired
    private LocationRepository locationRepository;

    private Order order;
    private Tool tool;
    private ToolsGroup group;
    private Booking booking;
    private Location location;

    @Before
    public void init() {
        order = new Order();
        group = new ToolsGroup();
        group.setName("name");
        location = new Location();
        location.setCountry("Russia");
        tool = ToolCreator.toolCreator();
        tool.setGroup(group);
        tool.setLocation(location);
        booking = new Booking();
        booking.setOrder(order);
        booking.setTool(tool);
        booking.setBookedDateFrom(LocalDate.of(2020, 10, 11));
        booking.setBookedDateTo(LocalDate.of(2020, 10, 25));
    }



    @Test
    public void testBookingDaoSave() {
        //Given
        orderRepository.save(order);
        groupRepository.save(group);
        locationRepository.save(location);
        toolRepository.save(tool);

        //When
        repository.save(booking);
        long bookingId = booking.getId();
        long orderId = order.getId();
        long groupId = group.getId();
        long locationId = location.getId();
        long toolId = tool.getId();


        //Then
        assertTrue(repository.count() > 0);
        assertEquals(LocalDate.of(2020, 10, 25), booking.getBookedDateTo());

        //CleanUp
        repository.deleteById(bookingId);
        toolRepository.deleteById(toolId);
        orderRepository.deleteById(orderId);
        groupRepository.deleteById(groupId);
        locationRepository.deleteById(locationId);
    }

    @Test
    public void testFindAllBookings() {
        //Given
        orderRepository.save(order);
        groupRepository.save(group);
        locationRepository.save(location);
        toolRepository.save(tool);

        //When
        repository.save(booking);
        long bookingId = booking.getId();
        long orderId = order.getId();
        long groupId = group.getId();
        long locationId = location.getId();
        long toolId = tool.getId();


        //Then
        assertTrue(repository.findAll().size() > 0);

        //CleanUp
        repository.deleteById(bookingId);
        toolRepository.deleteById(toolId);
        orderRepository.deleteById(orderId);
        groupRepository.deleteById(groupId);
        locationRepository.deleteById(locationId);
    }

    @Test
    public void testRemoveBooking() {

        //Given
        orderRepository.save(order);
        groupRepository.save(group);
        locationRepository.save(location);
        toolRepository.save(tool);

        //When
        repository.save(booking);
        int number = repository.findAll().size();
        long bookingId = booking.getId();
        repository.deleteById(bookingId);
        int numberAfterDelete = repository.findAll().size();


        long orderId = order.getId();
        long groupId = group.getId();
        long locationId = location.getId();
        long toolId = tool.getId();


        //Then
        assertEquals(number, numberAfterDelete);

        //CleanUp
        repository.deleteById(bookingId);
        toolRepository.deleteById(toolId);
        orderRepository.deleteById(orderId);
        groupRepository.deleteById(groupId);
        locationRepository.deleteById(locationId);
    }

    @Test
    public void testFindBookingById() {
        //Given
        orderRepository.save(order);
        groupRepository.save(group);
        locationRepository.save(location);
        toolRepository.save(tool);

        //When
        repository.save(booking);
        long bookingId = booking.getId();
        long orderId = order.getId();
        long groupId = group.getId();
        long locationId = location.getId();
        long toolId = tool.getId();


        //Then
        assertTrue(repository.findAll().size() > 0);
        assertEquals(LocalDate.of(2020, 10, 11), repository.findBookingsById(bookingId).getBookedDateFrom());

        //CleanUp
        repository.deleteById(bookingId);
        toolRepository.deleteById(toolId);
        orderRepository.deleteById(orderId);
        groupRepository.deleteById(groupId);
        locationRepository.deleteById(locationId);
    }
}
