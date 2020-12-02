package com.service;

import com.domain.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(long id);

    User getUserByEmail(String mail);

    void delete(long id);

    void deleteAll();

    //User addUser(User user) throws EmailExistsException;

    void addRoleToUser(long userId, long roleId);
}
