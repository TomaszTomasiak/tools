package com.service;

import com.domain.User;
import com.dto.UserDto;

import com.dto.UserRegistrationDto;
import com.exception.EmailExistsException;
import com.exception.LoginExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDto appUserDTO);

    List<User> findAll();

    Optional<User> findById(Long id);

    void delete(Long id);

    void deleteAll();



    public User getUserByLogin(String login);

    public User addUser(UserRegistrationDto user) throws EmailExistsException, LoginExistsException;

    public int updateUser(String userlogin, User userUpdate);

    public void addRoleToUser(Long userId, Long roleId);
}
