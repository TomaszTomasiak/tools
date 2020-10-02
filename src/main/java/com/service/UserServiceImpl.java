package com.service;

import com.domain.User;
import com.dto.UserDto;
import com.dto.UserRegistrationDto;
import com.exception.EmailExistsException;
import com.exception.LoginExistsException;
import com.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User save(final User user) {
        log.debug("Request to save User : {}", user);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        log.debug("Request to get all users");
        return userRepository.findAll();

    }

    @Override
    public Optional<User> findById(Long id) {
        log.debug("Request to get user with id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to remove user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to remove all users");
        userRepository.deleteAll();
    }

    @Override
    public Optional<User> getUserByMail(String mail) {
        return userRepository.findUserByMail(mail);
    }

    @Override
    public User addUser(UserRegistrationDto user) throws EmailExistsException {

        if (checkEmailExists(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress: " + user.getEmail());
        }
        return userRepository.addUser(user);
    }


    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        //to implement
    }

    public boolean checkEmailExists(String email) {

        Optional<User> user = userRepository.findUserByMail(email);

        if (user.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
