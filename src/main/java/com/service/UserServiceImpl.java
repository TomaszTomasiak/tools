package com.service;

import com.domain.User;
import com.dto.UserDto;
import com.dto.UserRegistrationDto;
import com.exception.EmailExistsException;
import com.exception.LoginExistsException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Override
    public User save(UserDto appUserDTO) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public User addUser(UserRegistrationDto user) throws EmailExistsException, LoginExistsException {
        return null;
    }

    @Override
    public int updateUser(String userlogin, User userUpdate) {
        return 0;
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {

    }
}
