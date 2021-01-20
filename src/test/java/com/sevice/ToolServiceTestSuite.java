package com.sevice;

import com.domain.Location;
import com.domain.Tool;
import com.domain.ToolsGroup;
import com.exception.NotFoundException;
import com.service.LocationServiceImpl;
import com.service.ToolServiceImpl;
import com.service.ToolsGroupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolServiceTestSuite {

    @Autowired
    private ToolServiceImpl service;

    @Autowired
    private ToolsGroupServiceImpl toolsGroupService;

    @Autowired
    private LocationServiceImpl locationService;

    @Test
    public void testSaveToolAndGetAllTools () {
        //Given

        int numberOfTools = service.getAllTools().size();
        ToolsGroup group = ToolsGroup.builder()
                .name("ogrodnicze")
                .build();
        toolsGroupService.saveGroup(group);

        Location location = Location.builder()
                .country("Poland")
                .city("Warsaw")
                .zipCode("00-950")
                .address("Woronicza 17")
                .email("email@test.pl")
                .phone("666111333")
                .build();
        locationService.saveLocation(location);

        Tool tool = Tool.builder()
                .rentRate(BigDecimal.TEN)
                .group(group)
                .location(location)
                .name("szpadel")
                .build();

        //When
        service.saveTool(tool);

        int numberOfToolsAfterToolSave = service.getAllTools().size();


        //Then
        assertEquals(1, numberOfToolsAfterToolSave - numberOfTools);
        assertTrue(numberOfToolsAfterToolSave > 0);
    }

    @Test
    public void testRemoveTool() {
        //Given

        int numberOfTools = service.getAllTools().size();
        ToolsGroup group = ToolsGroup.builder()
                .name("ogrodnicze")
                .build();
        toolsGroupService.saveGroup(group);

        Location location = Location.builder()
                .country("Poland")
                .city("Warsaw")
                .zipCode("00-950")
                .address("Woronicza 17")
                .email("email@test.pl")
                .phone("666111333")
                .build();
        locationService.saveLocation(location);

        Tool tool = Tool.builder()
                .rentRate(BigDecimal.TEN)
                .group(group)
                .location(location)
                .name("szpadel")
                .build();

        //When
        service.saveTool(tool);
        long toolId = tool.getId();
        service.deleteTool(toolId);
        int numberOfToolsAfterToolRemove = service.getAllTools().size();

        //Then
        assertEquals(0, numberOfToolsAfterToolRemove - numberOfTools);
        assertFalse(service.getTool(toolId).isPresent());
    }

    @Test
    public void testReturnToolById() throws NotFoundException {
        //Given

        int numberOfTools = service.getAllTools().size();
        ToolsGroup group = ToolsGroup.builder()
                .name("ogrodnicze")
                .build();
        toolsGroupService.saveGroup(group);

        Location location = Location.builder()
                .country("Poland")
                .city("Warsaw")
                .zipCode("00-950")
                .address("Woronicza 17")
                .email("email@test.pl")
                .phone("666111333")
                .build();
        locationService.saveLocation(location);

        Tool tool = Tool.builder()
                .rentRate(BigDecimal.TEN)
                .group(group)
                .location(location)
                .name("szpadel")
                .build();

        //When
        service.saveTool(tool);
        long toolId = tool.getId();

        Tool tool1 = service.getTool(toolId).orElseThrow(NotFoundException::new);

        //Then
        assertEquals(toolId, tool1.getId());
        assertEquals(tool.getLocation().getId(), tool1.getLocation().getId());
        assertEquals(tool.getGroup().getName(), tool1.getGroup().getName());
    }

    @Test(expected = NotFoundException.class)
    public void testShoudNotReturnToolById() throws NotFoundException {
        //Given
        //When
        Tool tool1 = service.getTool(55555).orElseThrow(NotFoundException::new);

    }
}
