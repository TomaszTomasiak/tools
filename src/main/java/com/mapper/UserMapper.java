package com.mapper;

import com.domain.User;
import com.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDto(final User user) {
        UserDto userDtoBean = new UserDto();
        userDtoBean.setId(user.getId());
        userDtoBean.setName(user.getName());
        userDtoBean.setSurname(user.getSurname());
        userDtoBean.setEmail(user.getEmail());
        userDtoBean.setPassword(user.getPassword());
        userDtoBean.setPhone(user.getPhone());
        userDtoBean.setPesel(user.getPesel());
        return userDtoBean;
    }

    public User toEntity(final UserDto userDto) {
        User userBean = new User();
        userBean.setId(userDto.getId());
        userBean.setName(userDto.getName());
        userBean.setSurname(userDto.getSurname());
        userBean.setEmail(userDto.getEmail());
        userBean.setPassword(userDto.getPassword());
        userBean.setPhone(userDto.getPhone());
        userBean.setPesel(userDto.getPesel());
        return userBean;
    }

    public List<UserDto> toDto(final List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
