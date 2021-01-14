package com.sevice;

import com.domain.ToolsGroup;
import com.service.ToolsGroupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolsGroupTestSuite {

    @Autowired
    private ToolsGroupServiceImpl service;

    private ToolsGroup toolsGroup = ToolsGroup.builder()
            .id(15)
            .name("budowlane")
            .build();

    @Test
    public void testGetAllAndSave() {
        //Given
        long numberOfGroupsBeforeAddUser = service.getAllGroups().size();
        //When
        service.saveGroup(toolsGroup);
        long numberOfGroupsAfterAddUser = service.getAllGroups().size();

        //Then

        assertEquals(1, numberOfGroupsAfterAddUser - numberOfGroupsBeforeAddUser);
        assertTrue(service.getAllGroups().size() > 0);
    }
}
