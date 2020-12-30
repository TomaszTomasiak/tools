package com.dao;

import com.domain.Location;
import com.mapper.LocationMapper;
import com.repository.LocationRepository;
import com.resourcesData.LocationDtoCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDaoTestSuite {

    @Autowired
    private LocationRepository dao;

    @Autowired
    private LocationMapper mapper;

    private Location location;

    @Before
    public void init() {
        location = mapper.mapToLocation(LocationDtoCreator.locationDtoCreator());
    }

    @Test
    public void testLocationDaoSave() {
        //Given
        //When
        dao.save(location);

        //Then
        assertTrue(dao.count() > 0);
        assertNotEquals(0, dao.findAll().size());
        assertEquals(LocationDtoCreator.COUNTRY, location.getCountry());

        //CleanUp
        dao.deleteAll();
    }

    @Test
    public void testFindAllLocations() {
        //Given
        //When
        dao.save(location);

        //Then
        assertTrue(dao.findAll().size() > 0);

        //CleanUp
        dao.deleteAll();
    }

    @Test
    public void testRemoveLocation() {

        //Given
        //When
        dao.save(location);

        long id = location.getId();
        int number = dao.findAll().size();
        dao.deleteById(id);

        //Then
        assertEquals(dao.findAll().size(), number - 1);

        //CleanUp
        dao.deleteAll();
    }

    @Test
    public void testFindLocationById() {
        //Given
        //When
        dao.save(location);

        long id = location.getId();
        //Then
        assertTrue(dao.findAll().size() > 0);
        assertEquals(LocationDtoCreator.EMAIL, location.getEmail());

        //CleanUp
        dao.deleteAll();
    }
}
