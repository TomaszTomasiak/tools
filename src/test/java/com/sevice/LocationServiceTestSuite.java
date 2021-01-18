package com.sevice;

import com.domain.Location;
import com.exception.NotFoundException;
import com.service.LocationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceTestSuite {

    @Autowired
    private LocationServiceImpl service;

    @Test
    public void testSaveLocation() {
        //Given
        int numberOfLocationsBeforeAddUser = service.getAllLocations().size();
        Location location = Location.builder()
                .country("Poland")
                .city("Warsaw")
                .zipCode("00-950")
                .address("Woronicza 17")
                .email("email@test.pl")
                .phone("666111333")
                .build();
        //When
        service.saveLocation(location);

        int numberOfLocationsAfterAddUser = service.getAllLocations().size();
        //Then
        assertEquals("Poland", location.getCountry());
        assertEquals(1, numberOfLocationsAfterAddUser - numberOfLocationsBeforeAddUser);
    }

    @Test
    public void testDeleteLocation() {
        //Given

        Location location1 = Location.builder()
                .country("Poland")
                .city("Warsaw")
                .zipCode("00-950")
                .address("Woronicza 17")
                .email("email@test.pl")
                .phone("666111333")
                .build();

        service.saveLocation(location1);
        long id  = location1.getId();

        //When
        service.deleteLocation(id);

        //Then
        assertFalse(service.getLocation(id).isPresent());
    }

    @Test
    public void testReturnLocationById() throws NotFoundException {
        //Given
        Location location = Location.builder()
                .country("Poland")
                .city("Warsaw")
                .zipCode("00-950")
                .address("Woronicza 17")
                .email("email@test.pl")
                .phone("666111333")
                .build();

        service.saveLocation(location);
        long id = location.getId();
        //When
        Location location1 = service.getLocation(location.getId()).orElseThrow(NotFoundException::new);
        //Then
        assertEquals(id, location1.getId());
        assertEquals(location.getAddress(), location1.getAddress());
        assertEquals(location.getPhone(), location1.getPhone());
    }

    @Test(expected = NotFoundException.class)
    public void testLocationByIdNotFound() throws NotFoundException {
        service.getLocation(2222).orElseThrow(NotFoundException::new);
    }
}
