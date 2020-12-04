package com.mapper;

import com.domain.User;
import com.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto mapToUserDtoList(final User user) {
        UserDto userDtoBean = new UserDto();
        userDtoBean.setId(user.getId());
        userDtoBean.setName(user.getName());
        userDtoBean.setSurname(user.getSurname());
        userDtoBean.setEmail(user.getEmail());
        userDtoBean.setPassword(user.getPassword());
        userDtoBean.setPhone(user.getPhone());
        userDtoBean.setPesel(user.getPesel());
        userDtoBean.setUserRoleSet(user.getUserRolesList());
        return userDtoBean;
    }

    public User mapToUser(final UserDto userDto) {
        User userBean = new User();
        userBean.setId(userDto.getId());
        userBean.setName(userDto.getName());
        userBean.setSurname(userDto.getSurname());
        userBean.setEmail(userDto.getEmail());
        userBean.setPassword(userDto.getPassword());
        userBean.setPhone(userDto.getPhone());
        userBean.setPesel(userDto.getPesel());
        userBean.setUserRolesList(userDto.getUserRoleSet());
        return userBean;
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(this::mapToUserDtoList)
                .collect(Collectors.toList());
    }

}
