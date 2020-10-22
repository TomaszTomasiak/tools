package com.controller;

import com.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.resourcesData.UserDtoCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    private UserDto userDto;
    private ObjectMapper mapper;


    @Before
    public void initTest() {
        userDto = UserDtoCreator.userDtoCreator();
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void schouldFetchEmptyListOfUsers() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        when(userController.getAllUsers()).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void schouldFetchNotEmptyListOfUsers() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        when(userController.getAllUsers()).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(userDto.getName())))
                .andExpect(jsonPath("$[0].email", is(userDto.getEmail())))
                .andExpect(jsonPath("$[0].password", is(userDto.getPassword())));
    }

    @Test
    @WithMockUser
    public void shouldGetUserWithIndicatedId() throws Exception {
        //Given

        when(userController.getUser(userDto.getId())).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/api/v1/users/"+userDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname", is(userDto.getSurname())))
                .andExpect(jsonPath("$.phone", is(userDto.getPhone())))
                .andExpect(jsonPath("$.password", is(userDto.getPassword())));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void shouldDeleteUser() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        when(userController.getAllUsers()).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/users/" + userDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void schouldUpdateUser() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        UserDto updatedUserDto = UserDtoCreator.updatedUserDtoCreator();
        when(userController.updateUserById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(UserDto.class)))).thenReturn(updatedUserDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedUserDto);

        //When & Then
        mockMvc.perform(put("/api/v1/users/"+userDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(87)))
                .andExpect(jsonPath("$.firstName", is(updatedUserDto.getName())))
                .andExpect(jsonPath("$.mailAdress", is(updatedUserDto.getEmail())));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void shouldCreateUser() throws Exception {
        //Given
        UserDto userDto1 = UserDtoCreator.userDtoCreator();
        when(userController.createUser(ArgumentMatchers.any(UserDto.class))).thenReturn(userDto1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto1);


        //When & Then
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.phone", is(userDto1.getPhone())))
                .andExpect(jsonPath("$.surname", is(userDto1.getSurname())));
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void shouldNotCreateUserBecauseUserWithIdAlreadyExists() throws Exception {

        userDto.setId(9L);

        when(userController.createUser(ArgumentMatchers.any(UserDto.class))).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);


        mockMvc.perform(post("/api/v1/ecommercee/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isForbidden());
    }
}
