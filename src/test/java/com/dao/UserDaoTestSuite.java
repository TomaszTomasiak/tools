package com.dao;


import com.domain.User;
import com.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTestSuite {

    @Autowired
    UserRepository userDao;

    @Test
    public void testUserDaoSave() {
        //Given
        User user = new User();
        user.setName("John");
        user.setSurname("Rambo");
        user.setEmail("john.rambo@gmail.com");
        user.setPassword("firstblood");
        user.setPesel("123123123");
        //When
        userDao.save(user);
        //List<User> users = userDao.findAll();
        Long id = user.getId();

        //Then
        assertTrue(userDao.count() > 0);
        assertFalse(userDao.findAll().size() == 0);
        assertEquals("John", user.getName());

        //CleanUp
        //userDao.deleteAll();
    }
}
