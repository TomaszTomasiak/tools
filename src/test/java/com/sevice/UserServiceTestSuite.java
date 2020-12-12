package com.sevice;


import com.domain.User;
import com.exception.NotFoundException;
import com.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestSuite {

    @Autowired
    private UserServiceImpl userService;

    private final User user = User.builder()
            .name("John")
            .surname("Rambo")
            .email("john.rambo@mail.com")
            .password("password")
            .phone("123456789")
            .build();

    @Test
    public void testSaveAndGetUser() {
        //Given
        long numberOfUsersBeforeAddUser = userService.findAll().size();
        //When
        userService.save(user);
        long id = user.getId();

        long numberOfUsersAfterAddUser = userService.findAll().size();


        //Then
        assertEquals("John", user.getName());
        assertEquals(1, numberOfUsersAfterAddUser - numberOfUsersBeforeAddUser);
    }

    @Test
    public void testDeleteUser() {
        //Given

        User newUser = user;
        //When
        userService.save(newUser);
        long id = newUser.getId();

        userService.delete(id);

        //Then
        assertFalse(userService.findById(newUser.getId()).isPresent());
    }

    @Test
    public void testReturnUserById() throws NotFoundException {
        //Given
        User userNew = User.builder()
                .name("Maryla")
                .surname("Rodowicz")
                .email("test@mail.com")
                .password("password2")
                .phone("222222222")
                .build();

        userService.save(userNew);

        //When
        User resultUser = userService.findById(userNew.getId()).orElseThrow(NotFoundException::new);
        //Then
        assertEquals(userNew.getId(), resultUser.getId());
        assertEquals(userNew.getSurname(), resultUser.getSurname());
        assertEquals(userNew.getPhone(), resultUser.getPhone());
    }
}