package com.service;

import com.domain.User;

import com.exception.EmailExistsException;
import com.exception.PeselExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User save(User user) throws EmailExistsException, PeselExistException;

    List<User> findAll();

    Optional<User> findById(long id);

    User getUserByEmail(String mail);

    void delete(Long id);


    //User addUser(User user) throws EmailExistsException;

    void addRoleToUser(long userId, long roleId);
}
