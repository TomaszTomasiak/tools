package com.dao;


import com.domain.User;
import com.mapper.UserMapper;
import com.repository.UserRepository;
import com.resourcesData.UserDtoCreator;
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
    UserMapper userMapper;

    @Autowired
    UserRepository userDao;

    @Test
    public void testUserDaoSave() {
        //Given

        User user = userMapper.mapToUser(UserDtoCreator.userDtoCreator());
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

    @Test
    public void testFindAllUsers() {
        //Given
        User user = userMapper.mapToUser(UserDtoCreator.userDtoCreator());
        //When
        userDao.save(user);
        //Then
        assertTrue(userDao.findAll().size() == 1);

    }

    @Test
    public void testRemoveUser( ) {
        //Given
        User user = userMapper.mapToUser(UserDtoCreator.userDtoCreator());
        //When
        userDao.save(user);
        long id = user.getId();
        userDao.deleteById(id);

        //Then
        assertTrue(userDao.findAll().size() == 0);
    }

}
