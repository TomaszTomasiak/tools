package com.controller;

import com.dto.LocationDto;
import com.google.gson.Gson;
import com.resourcesData.LocationDtoCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LocationController.class)
public class LocationControllerTestSuite {

    @MockBean
    private LocationController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void schouldFetchEmptyListOfLocations() throws Exception {
        //Given
        List<LocationDto> locationDtos = new ArrayList<>();
        when(controller.getAllLocations()).thenReturn(locationDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/locations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfLocations() throws Exception {
        //Given
        LocationDto locationDto = LocationDtoCreator.locationDtoCreator();
        List<LocationDto> locationDtos = new ArrayList<>();
        locationDtos.add(locationDto);
        when(controller.getAllLocations()).thenReturn(locationDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/locations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].country", is(locationDto.getCountry())))
                .andExpect(jsonPath("$[0].city", is(locationDto.getCity())))
                .andExpect(jsonPath("$[0].email", is(locationDto.getEmail())));
    }

    @Test
    public void shouldGetLocationWithIndicatedId() throws Exception {
        //Given
        LocationDto locationDto = LocationDtoCreator.locationDtoCreator();
        long id = locationDto.getId();
        when(controller.getLocation(id)).thenReturn(locationDto);

        //When & Then
        mockMvc.perform(get("/api/v1/locations/"+locationDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(locationDto.getEmail())))
                .andExpect(jsonPath("$.zipCode", is(locationDto.getZipCode())))
                .andExpect(jsonPath("$.address", is(locationDto.getAddress())));
    }

    @Test
    public void shouldDeleteLocation() throws Exception {
        //Given
        LocationDto locationDto = LocationDtoCreator.locationDtoCreator();
        List<LocationDto> locationDtos = new ArrayList<>();
        locationDtos.add(locationDto);
        when(controller.getAllLocations()).thenReturn(locationDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/locations/" + locationDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateLocation() throws Exception {
        //Given
        LocationDto locationDto = LocationDtoCreator.locationDtoCreator();
//        List<LocationDto> locationDtos = new ArrayList<>();
//        locationDtos.add(locationDto);
        LocationDto updatedLocationDto = LocationDtoCreator.updatedLocationDtoCreator();
        when(controller.updateLocationById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(LocationDto.class)))).thenReturn(updatedLocationDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedLocationDto);

        //When & Then
        mockMvc.perform(put("/api/v1/locations/"+locationDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
//                .andExpect(jsonPath("$.id", is(87)))
                .andExpect(jsonPath("$.city", is(updatedLocationDto.getCity())))
                .andExpect(jsonPath("$.phone", is(updatedLocationDto.getPhone())))
                .andExpect(jsonPath("$.email", is(updatedLocationDto.getEmail())));
    }

    @Test
    public void shouldCreateLocation() throws Exception {
        //Given
        LocationDto locationDto = LocationDtoCreator.locationDtoCreator();
        when(controller.createLocation(ArgumentMatchers.any(LocationDto.class))).thenReturn(locationDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(locationDto);


        //When & Then
        mockMvc.perform(post("/api/v1/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(locationDto.getEmail())))
                .andExpect(jsonPath("$.zipCode", is(locationDto.getZipCode())))
                .andExpect(jsonPath("$.address", is(locationDto.getAddress())));
    }

    @Test
    public void shouldNotCreateToolBecauseToolWithIdAlreadyExists() throws Exception {
        LocationDto locationDto = LocationDtoCreator.locationDtoCreator();
        locationDto.setId(1L);

        when(controller.createLocation(ArgumentMatchers.any(LocationDto.class))).thenReturn(locationDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(locationDto);


        mockMvc.perform(post("/api/v1/ecommercee/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isNotFound());
    }
}
