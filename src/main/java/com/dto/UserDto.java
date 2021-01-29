package com.dto;


import com.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String pesel;

    @JsonIgnore
    private Set<UserRole> userRoleSet;
}
