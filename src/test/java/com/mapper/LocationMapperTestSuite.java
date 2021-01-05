package com.mapper;

import com.domain.Location;
import com.dto.LocationDto;
import com.resourcesData.LocationDtoCreator;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationMapperTestSuite {

    @Autowired
    private LocationMapper mapper;

    private LocationDto dto = LocationDtoCreator.locationDtoCreator();

    private Location location;


}
