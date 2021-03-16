package com.dao;
import com.domain.*;
import com.repository.*;
import com.resourcesData.ToolCreator;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolDaoTestSuite {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ToolsGroupRepository groupRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ModelRepository modelRepository;

    @AfterEach
    public void clearUp() {
        producerRepository.deleteAll();
        modelRepository.deleteAll();
        toolRepository.deleteAll();
        groupRepository.deleteAll();
        locationRepository.deleteAll();
    }

    @Test
    public void testToolDaoSave() {
        //Given
        ToolsGroup group = new ToolsGroup();
        group.setName("name");
        groupRepository.save(group);
        Location location = new Location();
        location.setCountry("Russia");
        locationRepository.save(location);
        Producer producer = new Producer();
        producer.setName("XYZ");
        producerRepository.save(producer);
        Model model = Model.builder().name("NNN").producer(producer).build();
        modelRepository.save(model);
        Tool tool = ToolCreator.toolCreator();
        tool.setName("wiertarka");
        tool.setGroup(group);
        tool.setLocation(location);
        tool.setProducer(producer);
        tool.setModel(model);

        //When
        toolRepository.save(tool);

        //Then
        assertTrue(toolRepository.count() > 0);
        assertNotEquals(0, toolRepository.findAll().size());
        assertEquals("wiertarka", tool.getName());

        //CleanUp
    }

    @Test
    public void testFindAllTools() {
        //Given
        ToolsGroup group = new ToolsGroup();
        group.setName("name");
        groupRepository.save(group);
        Location location = new Location();
        location.setCountry("Russia");
        locationRepository.save(location);
        Producer producer = new Producer();
        producer.setName("XYZ");
        producerRepository.save(producer);
        Model model = Model.builder().name("NNN").producer(producer).build();
        modelRepository.save(model);
        Tool tool = ToolCreator.toolCreator();
        tool.setName("wiertarka");
        tool.setGroup(group);
        tool.setLocation(location);
        tool.setProducer(producer);
        tool.setModel(model);

        //When
        toolRepository.save(tool);

        //Then
        assertTrue(toolRepository.findAll().size() > 0);

        //CleanUp
    }

    @Test
    public void testRemoveTool() {

        //Given
        ToolsGroup group = new ToolsGroup();
        group.setName("name");
        groupRepository.save(group);
        Location location = new Location();
        location.setCountry("Russia");
        locationRepository.save(location);
        Producer producer = new Producer();
        producer.setName("XYZ");
        producerRepository.save(producer);
        Model model = Model.builder().name("NNN").producer(producer).build();
        modelRepository.save(model);
        Tool tool = ToolCreator.toolCreator();
        tool.setName("wiertarka");
        tool.setGroup(group);
        tool.setLocation(location);
        tool.setProducer(producer);
        tool.setModel(model);

        //When
        toolRepository.save(tool);
        long id = tool.getId();
        int number = toolRepository.findAll().size();
        toolRepository.deleteById(id);

        //Then
        assertEquals(toolRepository.findAll().size(), number - 1);

        //CleanUp
    }

    @Test
    public void testFindToolById() {
        //Given
        ToolsGroup group = new ToolsGroup();
        group.setName("name");
        groupRepository.save(group);
        Location location = new Location();
        location.setCountry("Russia");
        locationRepository.save(location);
        Producer producer = new Producer();
        producer.setName("XYZ");
        producerRepository.save(producer);
        Model model = Model.builder().name("NNN").producer(producer).build();
        modelRepository.save(model);
        Tool tool = ToolCreator.toolCreator();
        tool.setName("wiertarka");
        tool.setGroup(group);
        tool.setLocation(location);
        tool.setProducer(producer);
        tool.setModel(model);
        toolRepository.save(tool);
        //When

        long id = tool.getId();
        Tool theTool = toolRepository.findToolById(id);

        //Then
        assertTrue(toolRepository.findAll().size() > 0);
        assertEquals("NNN", theTool.getModel().getName());

        //CleanUp

    }
}
