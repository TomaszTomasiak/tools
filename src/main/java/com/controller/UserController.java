package com.controller;

import com.dto.UserDto;
import com.exception.NotFoundException;
import com.mapper.UserMapper;
import com.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private UserMapper mapper;


    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.debug("REST request to get all users");
        return mapper.toDto(service.findAll());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get user with id: {}", id);
        return mapper.toDto(service.findById(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        log.debug("REST request to add new user: {}", userDto);
        service.save(mapper.toEntity(userDto));
        return userDto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUserById(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        log.debug("REST request to update user with id: {}", id);
        return mapper.toDto(service.save(mapper.toEntity(userDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        log.debug("REST request to delete user with id: {}", id);
        service.delete(id);
    }
}
