package com.sevice;

import com.domain.Location;
import com.domain.Tool;
import com.domain.ToolsGroup;
import com.service.LocationServiceImpl;
import com.service.ToolServiceImpl;
import com.service.ToolsGroupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        long groupId = group.getId();

        Location location = Location.builder()
                .country("Poland")
                .city("Warsaw")
                .zipCode("00-950")
                .address("Woronicza 17")
                .email("email@test.pl")
                .phone("666111333")
                .build();
        locationService.saveLocation(location);
        long locationId = location.getId();

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
}
