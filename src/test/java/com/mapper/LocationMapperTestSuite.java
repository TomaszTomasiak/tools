package com.mapper;

import com.domain.Location;
import com.dto.LocationDto;
import com.resourcesData.LocationDtoCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationMapperTestSuite {

    @Autowired
    private LocationMapper mapper;

    private LocationDto dto = LocationDtoCreator.locationDtoCreator();

    private Location location = Location.builder()
            .id(5L)
            .country("USA")
            .city("New York")
            .zipCode("22222")
            .address("Wall St 1")
            .email("emai@wall.us")
            .phone("5551515")
            .build();

    @Test
    public void mapToLocation() {
        //Given
        dto.setId(1L);

        //When
        Location location1 = mapper.mapToLocation(dto);


        //Then
        assertNotNull(location1);
        assertEquals(1L, location1.getId());
        assertEquals("biuro@toolrent.pl", location1.getEmail());
        assertEquals("Poland", location1.getCountry());
    }

    @Test
    public void mapToLocationDto() {
        //Given

        //When
        LocationDto locationDto = mapper.mapToLocationDto(location);

        //Then
        assertNotNull(locationDto);
        assertEquals(5L, locationDto.getId());
        assertEquals("Wall St 1", locationDto.getAddress());
        assertEquals("5551515", locationDto.getPhone());

    }

    @Test
    public void mapToLocationDtoList() {
        //Given

        List<Location> locations = new ArrayList<>();
        locations.add(location);

        //When
        List<LocationDto> locationDtoList = mapper.mapToLocationDtoList(locations);

        //Then
        assertNotNull(locationDtoList);
        assertEquals(1, locationDtoList.size());
        assertEquals("New York", locationDtoList.get(0).getCity());
    }
}
