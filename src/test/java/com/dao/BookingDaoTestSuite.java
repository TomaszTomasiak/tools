package com.dao;

import com.domain.Booking;
import com.domain.Tool;
import com.domain.ToolsGroup;
import com.domain.User;
import com.mapper.BookingMapper;
import com.repository.BookingRepository;
import com.repository.ToolRepository;
import com.repository.ToolsGroupRepository;
import com.repository.UserRepository;
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
    ToolRepository toolRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ToolsGroupRepository groupRepository;

    @Test
    public void testBookingDaoSave() {
        //Given

        User user = UserCreator.userDtoCreator();
        userRepository.save(user);

        ToolsGroup toolsGroup = new ToolsGroup();
        toolsGroup.setName("name");
        groupRepository.save(toolsGroup);

        Tool tool = ToolCreator.toolCreator();
        tool.setGroupId(toolsGroup);
        toolRepository.save(tool);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTool(tool);
        booking.setBookedDateFrom(LocalDate.of(2020, 10, 11));
        booking.setBookedDateTo(LocalDate.of(2020, 10, 25));

                //When
        dao.save(booking);

        Long id = booking.getId();

        //Then
        assertTrue(dao.count() > 0);
        assertEquals(LocalDate.of(2020, 10, 25), booking.getBookedDateTo());

        //CleanUp
//        dao.deleteAll();
//        userRepository.deleteAll();
//        toolRepository.deleteAll();
    }

}
