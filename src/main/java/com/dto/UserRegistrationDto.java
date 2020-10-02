package com.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.validator.PasswordMatches;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@PasswordMatches
public class UserRegistrationDto implements Serializable {

    @NotNull
    @Size(max = 40)
    private String name;

    @NotNull
    @Size(max = 40)
    private String surname;

    @NotNull
    @Size(min = 6, max = 40)
    private String login;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    @Size(min = 6, max = 20)
    private String passwordMatches;

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 20)
    private String phone;

    @NotNull
    @Size(min = 11, max = 11, message = "Pesel must have 11 digits")
    private String pesel;

    public UserRegistrationDto() {
        super();
    }
}
