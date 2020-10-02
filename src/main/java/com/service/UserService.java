package com.service;

import com.domain.User;

import com.dto.UserRegistrationDto;
import com.exception.EmailExistsException;
import com.exception.LoginExistsException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User>getUserByMail(String mail);

    void delete(Long id);

    void deleteAll();

    User addUser(UserRegistrationDto user) throws EmailExistsException;

    void addRoleToUser(Long userId, Long roleId);
}
