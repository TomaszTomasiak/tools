package com.sevice;


import com.domain.User;
import com.exception.EmailExistsException;
import com.exception.NotFoundException;
import com.exception.PeselExistException;
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
    public void testSaveUser() throws EmailExistsException, PeselExistException {
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
    public void testDeleteUser() throws EmailExistsException, PeselExistException {
        //Given

        User newUser = user;
        user.setEmail("new@email.com");
        user.setPesel("999999999");
        //When
        userService.save(newUser);
        long id = newUser.getId();

        userService.delete(id);

        //Then
        assertFalse(userService.findById(newUser.getId()).isPresent());
    }

    @Test
    public void testReturnUserById() throws NotFoundException, EmailExistsException, PeselExistException {
        //Given
        User userNew = User.builder()
                .name("Maryla")
                .surname("Rodowicz")
                .email("test6@mail.com")
                .password("password2")
                .pesel("1234562220")
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

    @Test(expected = NotFoundException.class)
    public void testUserByIdNotFound() throws NotFoundException, EmailExistsException, PeselExistException {
        //Given
        User userNew = User.builder()
                .name("Maryla")
                .surname("Rodowicz")
                .email("test@mail.com")
                .password("password2")
                .pesel("1234567890")
                .phone("222222222")
                .build();

        userService.save(userNew);

        //When
        User resultUser = userService.getUserByEmail("test@mail.com");
        User userNotFound = userService.findById(2222).orElseThrow(NotFoundException::new);

        //Then
        assertSame(NotFoundException.class, NotFoundException.class);
        assertEquals("Maryla", resultUser.getName());
    }

    @Test(expected = EmailExistsException.class)
    public void testUserEmailExistsException() throws EmailExistsException, PeselExistException {
        //Given
        User userNew = User.builder()
                .name("Maryla")
                .surname("Rodowicz")
                .email("john.rambo@mail.com")
                .password("password2")
                .pesel("1234567890")
                .phone("222222222")
                .build();

        User userNew1 = User.builder()
                .name("Maryla")
                .surname("Rodowicz")
                .email("john.rambo@mail.com")
                .password("password2")
                .pesel("1234567890")
                .phone("222222222")
                .build();

        userService.save(userNew);

        //When
        userService.save(userNew1);
        //Then

    }

    @Test(expected = PeselExistException.class)
    public void testUserPeselExistsException() throws EmailExistsException, PeselExistException {
        //Given
        User userNew = User.builder()
                .name("Maryla")
                .surname("Rodowicz")
                .email("maryla@mail.com")
                .password("password2")
                .pesel("1234567890")
                .phone("222222222")
                .build();

        User userNew1 = User.builder()
                .name("Maryla")
                .surname("Rodowicz")
                .email("test1@test.pl")
                .password("password2")
                .pesel("1234567890")
                .phone("222222222")
                .build();

        userService.save(userNew);

        //When
        userService.save(userNew1);

        //Then

    }
}