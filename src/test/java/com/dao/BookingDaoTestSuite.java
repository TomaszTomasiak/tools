package com.dao;

import com.domain.*;
import com.repository.*;
import com.resourcesData.ToolCreator;
import com.resourcesData.UserCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private ToolsGroupRepository groupRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ModelRepository modelRepository;

//    @BeforeEach
//    public void init() {
//        ToolsGroup group = new ToolsGroup();
//        group.setName("name");
//        groupRepository.save(group);
//        Location location = new Location();
//        location.setCountry("Russia");
//        locationRepository.save(location);
//        Producer producer = new Producer();
//        producer.setName("XYZ");
//        producerRepository.save(producer);
//        Model model = Model.builder().name("NNN").producer(producer).build();
//        modelRepository.save(model);
//        Tool tool = ToolCreator.toolCreator();
//        tool.setGroup(group);
//        tool.setLocation(location);
//        tool.setProducer(producer);
//        tool.setModel(model);
//        toolRepository.save(tool);
//        User user = UserCreator.userCreator();
//        userRepository.save(user);
//        Cart cart = new Cart();
//        cart.setUser(user);
//        cartRepository.save(cart);
//    }

    @AfterEach
    public void clearUp(){

        producerRepository.deleteAll();
        modelRepository.deleteAll();
        toolRepository.deleteAll();
        groupRepository.deleteAll();
        locationRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
        repository.deleteAll();
    }


    @Test
    public void testBookingDaoSave() {
        //Given
        ToolsGroup group = new ToolsGroup();
        group.setName("name");
        groupRepository.save(group);
        Location location = new Location();
        location.setCountry("Russia");
        locationRepository.save(location);
        Producer producer = new Producer();
        producer.setName("XYZ");
        producerRepository.save(producer);
        Model model = Model.builder().name("NNN").producer(producer).build();
        modelRepository.save(model);
        Tool tool = ToolCreator.toolCreator();
        tool.setGroup(group);
        tool.setLocation(location);
        tool.setProducer(producer);
        tool.setModel(model);
        toolRepository.save(tool);
        User user = UserCreator.userCreator();
        userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        Booking booking = new Booking();
        booking.setCart(cart);
        booking.setTool(tool);
        booking.setBookedDateFrom(LocalDate.of(2020, 10, 11));
        booking.setBookedDateTo(LocalDate.of(2020, 10, 25));
        //When
        repository.save(booking);

        //Then
        assertTrue(repository.count() > 0);
        assertEquals(LocalDate.of(2020, 10, 25), booking.getBookedDateTo());

        //CleanUp

    }

    @Test
    public void testFindAllBookings() {
        //Given
        ToolsGroup group = new ToolsGroup();
        group.setName("name");
        groupRepository.save(group);
        Location location = new Location();
        location.setCountry("Russia");
        locationRepository.save(location);
        Producer producer = new Producer();
        producer.setName("XYZ");
        producerRepository.save(producer);
        Model model = Model.builder().name("NNN").producer(producer).build();
        modelRepository.save(model);
        Tool tool = ToolCreator.toolCreator();
        tool.setGroup(group);
        tool.setLocation(location);
        tool.setProducer(producer);
        tool.setModel(model);
        toolRepository.save(tool);
        User user = UserCreator.userCreator();
        userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        Booking booking = new Booking();
        booking.setCart(cart);
        booking.setTool(tool);
        booking.setBookedDateFrom(LocalDate.of(2020, 10, 11));
        booking.setBookedDateTo(LocalDate.of(2020, 10, 25));

        //When
        repository.save(booking);
        long bookingId = booking.getId();
        long groupId = group.getId();
        long locationId = location.getId();
        long toolId = tool.getId();


        //Then
        assertTrue(repository.findAll().size() > 0);

        //CleanUp
        repository.deleteById(bookingId);
        toolRepository.deleteById(toolId);
        groupRepository.deleteById(groupId);
        locationRepository.deleteById(locationId);
    }

    @Test
    public void testRemoveBooking() {

        //Given
        ToolsGroup group = new ToolsGroup();
        group.setName("name");
        groupRepository.save(group);
        Location location = new Location();
        location.setCountry("Russia");
        locationRepository.save(location);
        Producer producer = new Producer();
        producer.setName("XYZ");
        producerRepository.save(producer);
        Model model = Model.builder().name("NNN").producer(producer).build();
        modelRepository.save(model);
        Tool tool = ToolCreator.toolCreator();
        tool.setGroup(group);
        tool.setLocation(location);
        tool.setProducer(producer);
        tool.setModel(model);
        toolRepository.save(tool);
        User user = UserCreator.userCreator();
        userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        Booking booking = new Booking();
        booking.setCart(cart);
        booking.setTool(tool);
        booking.setBookedDateFrom(LocalDate.of(2020, 10, 11));
        booking.setBookedDateTo(LocalDate.of(2020, 10, 25));

        //When
        repository.save(booking);
        int number = repository.findAll().size();
        long bookingId = booking.getId();
        repository.findBookingsById(bookingId);

        repository.deleteById(bookingId);
        int numberAfterDelete = repository.findAll().size();

        long groupId = group.getId();
        long locationId = location.getId();
        long toolId = tool.getId();


        //Then
        assertEquals(number -1 , numberAfterDelete);

        //CleanUp
        repository.deleteById(bookingId);
        toolRepository.deleteById(toolId);
        groupRepository.deleteById(groupId);
        locationRepository.deleteById(locationId);
    }

    @Test
    public void testFindBookingById() {
        //Given
        ToolsGroup group = new ToolsGroup();
        group.setName("name");
        groupRepository.save(group);
        Location location = new Location();
        location.setCountry("Russia");
        locationRepository.save(location);
        Producer producer = new Producer();
        producer.setName("XYZ");
        producerRepository.save(producer);
        Model model = Model.builder().name("NNN").producer(producer).build();
        modelRepository.save(model);
        Tool tool = ToolCreator.toolCreator();
        tool.setGroup(group);
        tool.setLocation(location);
        tool.setProducer(producer);
        tool.setModel(model);
        toolRepository.save(tool);
        User user = UserCreator.userCreator();
        userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        Booking booking = new Booking();

        booking.setCart(cart);
        booking.setTool(tool);
        booking.setBookedDateFrom(LocalDate.of(2020, 10, 11));
        booking.setBookedDateTo(LocalDate.of(2020, 10, 25));

        //When
        repository.save(booking);
        long bookingId = booking.getId();
        long groupId = group.getId();
        long locationId = location.getId();
        long toolId = tool.getId();


        //Then
        assertTrue(repository.findAll().size() > 0);
        assertEquals(LocalDate.of(2020, 10, 11), repository.findBookingsById(bookingId).getBookedDateFrom());

        //CleanUp
        repository.deleteById(bookingId);
        toolRepository.deleteById(toolId);
        groupRepository.deleteById(groupId);
        locationRepository.deleteById(locationId);
    }
}
