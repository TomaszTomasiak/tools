package com.dao;

import com.domain.Booking;
import com.domain.User;
import com.mapper.BookingMapper;
import com.repository.BookingRepository;
import com.resourcesData.ToolCreator;
import com.resourcesData.UserCreator;
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
    BookingRepository dao;

    @Autowired
    BookingMapper mapper;

    @Test
    public void testBookingDaoSave() {
        //Given

        User user = UserCreator.userDtoCreator();

        Booking booking = new Booking();
        booking.setId(1L);
        booking.setUser(user);
        booking.setTool(ToolCreator.toolCreator());
        booking.setBookedDateFrom(LocalDate.of(2020, 10, 11));
        booking.setBookedDateTo(LocalDate.of(2020, 10, 25));

        //When
        dao.save(booking);

        Long id = booking.getId();

        //Then
        assertTrue(dao.count() > 0);
        assertFalse(dao.findAll().size() == 0);
        assertEquals(LocalDate.of(2020, 06, 14), booking.getBookedDateTo());

        //CleanUp
        //dao.deleteAll();
    }

}
