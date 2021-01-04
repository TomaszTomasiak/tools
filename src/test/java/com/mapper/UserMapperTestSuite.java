package com.mapper;

import com.domain.User;
import com.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    private UserMapper mapper;

    private UserDto userDto = UserDto.builder()
            .id(1L)
            .name("John")
            .surname("Rambo")
            .email("john.rambo@mail.com")
            .password("password")
            .phone("123456789")
            .build();

    private User user = User.builder()
            .id(1L)
            .name("John")
            .surname("Rambo")
            .email("john.rambo@mail.com")
            .password("password")
            .phone("123456789")
            .build();

    @Test
    public void mapToUser() {
        //Given

        //When
        User user = mapper.mapToUser(userDto);


        //Then
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("John", user.getName());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void mapToUserDto() {
        //Given

        //When
        UserDto userDto = mapper.mapToUserDto(user);

        //Then
        assertNotNull(userDto);
        assertEquals(1L, userDto.getId());
        assertEquals("Rambo", userDto.getSurname());
        assertEquals("john.rambo@mail.com", userDto.getEmail());

    }

    @Test
    public void mapToUserDtoList() {
        //Given

        List<User> users = new ArrayList<>();
        users.add(user);

        //When
        List<UserDto> usersDtoList = mapper.mapToUserDto(users);

        //Then
        assertNotNull(usersDtoList);
        assertEquals(1, usersDtoList.size());
        assertEquals("John", usersDtoList.get(0).getName());
    }
}