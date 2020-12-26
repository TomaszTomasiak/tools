package com.controller;

import com.dto.LocationDto;
import com.dto.ToolDto;
import com.exception.NotFoundException;
import com.mapper.LocationMapper;
import com.service.LocationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
public class LocationController {

    @Autowired
    private LocationMapper mapper;

    @Autowired
    private LocationServiceImpl service;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<LocationDto> getAllLocations() {
        log.debug("REST request to get all locations");
        return mapper.mapToLocationDtoList(service.getAllLocations());
    }

    @GetMapping("/{id}")
    public LocationDto getLocation(@PathVariable("id") long id) throws NotFoundException {
        log.debug("REST request to get location with id: {}", id);
        return mapper.mapToLocationDto(service.getLocation(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public LocationDto createLocation(@RequestBody LocationDto dto) {
        log.debug("REST request to add new location: {}", dto);
        service.saveLocation(mapper.mapToLocation(dto));
        return dto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LocationDto updateLocationById(@PathVariable("id") long id, @RequestBody LocationDto dto) {
        log.debug("REST request to update location with id: {}", id);
        return mapper.mapToLocationDto(service.saveLocation(mapper.mapToLocation(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteLocationById(@PathVariable("id") long id) {
        log.debug("REST request to delete location with id: {}", id);
        service.deleteLocation(id);
    }
}
