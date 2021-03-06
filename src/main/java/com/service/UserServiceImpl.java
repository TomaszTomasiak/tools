package com.service;

import com.domain.User;
import com.exception.EmailExistsException;
import com.exception.PeselExistException;
import com.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User save(final User user) throws EmailExistsException, PeselExistException {
        log.debug("Request to save User : {}", user);
        if (checkEmailExists(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress: " + user.getEmail());
        }
        if (checkPeselExists(user.getPesel())) {
            throw new PeselExistException("There is account with pesel: " + user.getPesel());
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        log.debug("Request to get all users");
        return userRepository.findAll();

    }

    @Override
    public Optional<User> findById(long id) {
        log.debug("Request to get user with id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to remove user with id: {}", id);
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String mail) {
        return userRepository.findUserByEmail(mail);
    }


    @Override
    public void addRoleToUser(long userId, long roleId) {
        //to implement
    }

    public boolean checkEmailExists(String email) {

        User user = userRepository.findUserByEmail(email);

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPeselExists(String pesel) {

        User user = userRepository.findUserByPesel(pesel);

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}
