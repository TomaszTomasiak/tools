package com.sevice;

import com.domain.Location;
import com.service.LocationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceTestSuite {

    @Autowired
    private LocationServiceImpl service;

    private Location location = Location.builder()
            .id(22)
            .country("Poland")
            .city("Warsaw")
            .zipCode("00-950")
            .address("Woronicza 17")
            .email("email@test.pl")
            .phone("666111333")
            .build();


    @Test
    public void testSaveLocation() {
        //Given
        int numberOfLocationsBeforeAddUser = service.getAllLocations().size();
        //When
        service.saveLocation(location);

        int numberOfLocationsAfterAddUser = service.getAllLocations().size();
        //Then
        assertEquals("Poland", location.getCountry());
        assertEquals(1, numberOfLocationsAfterAddUser - numberOfLocationsBeforeAddUser);
    }


}
