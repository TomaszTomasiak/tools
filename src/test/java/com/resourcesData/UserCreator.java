package com.resourcesData;


import com.domain.User;

public class UserCreator {
    public static String FIRST_NAME = "John";
    public static String UPDATED_NAME = "Maryla";
    public static String LAST_NAME = "Rambo";
    public static String UPDATED_LAST_NAME = "Rodowicz";
    public static String MAIL = "john.rambo@test.pl";
    public static String UPDATED_MAIL = "maryla.rodowicz@test.pl";
    public static String PHONE = "123456789";
    public static String UPDATED_PHONE = "987654321";
    public static String PASSWORD = "password";
    public static String UPDATED_PASSWORD = "updated_password";
    public static String PESEL = "123456789";
    public static String UPDATED_PESEL = "987654321";

    public static User userDtoCreator() {
        return User.builder()
//                .id(USER_ID)
                .name(FIRST_NAME)
                .surname(LAST_NAME)
                .email(MAIL)
                .password(PASSWORD)
                .phone(PHONE)
                .pesel(PESEL)
                .build();
    }

    public static User updatedUserDtoCreator() {
        return User.builder()
//                .id(UPDATED_USER_ID)
                .name(UPDATED_NAME)
                .surname(UPDATED_LAST_NAME)
                .email(UPDATED_MAIL)
                .password(UPDATED_PASSWORD)
                .phone(UPDATED_PHONE)
                .phone(UPDATED_PESEL)
                .build();
    }
}
