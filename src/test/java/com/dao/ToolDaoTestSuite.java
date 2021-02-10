package com.dao;
import com.domain.*;
import com.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolDaoTestSuite {
    @Autowired
    private ToolsGroupRepository groupDao;

    @Autowired
    private ToolRepository toolDao;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ModelRepository modelRepository;

    private ToolsGroup toolsGroup;
    private Tool tool;
    private Location location;
    private Producer producer;
    private Model model;

    @Before
    public void init() {
        toolsGroup = new ToolsGroup();
        toolsGroup.setName("budowlane");

        location = new Location();
        location.setCity("Warsaw");

        producer = new Producer();
        producer.setName("Bosch");

        model = new Model();
        model.setProducer(producer);
        model.setName("XYZ123");

        tool = new Tool();
        tool.setName("wiertarka");
    }

    @Test
    public void testToolDaoSave() {
        //Given
        //When
        groupDao.save(toolsGroup);
        locationRepository.save(location);
        producerRepository.save(producer);
        modelRepository.save(model);
        tool.setGroup(toolsGroup);
        tool.setLocation(location);
        tool.setModel(model);
        tool.setProducer(producer);
        toolDao.save(tool);

        //Then
        assertTrue(toolDao.count() > 0);
        assertNotEquals(0, toolDao.findAll().size());
        assertEquals("wiertarka", tool.getName());

        //CleanUp
        toolDao.deleteAll();
        groupDao.deleteAll();
    }

    @Test
    public void testFindAllTools() {
        //Given
        //When
        groupDao.save(toolsGroup);
        tool.setGroup(toolsGroup);
        toolDao.save(tool);
        //Then
        assertTrue(toolDao.findAll().size() > 0);

        //CleanUp
        toolDao.deleteAll();
        groupDao.deleteAll();
    }

    @Test
    public void testRemoveTool() {

        //Given
        //When
        groupDao.save(toolsGroup);
        tool.setGroup(toolsGroup);
        toolDao.save(tool);
        long id = tool.getId();
        int number = toolDao.findAll().size();
        toolDao.deleteById(id);

        //Then
        assertEquals(toolDao.findAll().size(), number - 1);

        //CleanUp
        toolDao.deleteAll();
        groupDao.deleteAll();
    }

    @Test
    public void testFindToolById() {
        //Given
        //When
        groupDao.save(toolsGroup);
        tool.setGroup(toolsGroup);
        toolDao.save(tool);
        long id = tool.getId();
        //Then
        assertTrue(toolDao.findAll().size() > 0);
        assertEquals("XYZ123", tool.getModel());

        //CleanUp
        toolDao.deleteAll();
        groupDao.deleteAll();
    }
}
