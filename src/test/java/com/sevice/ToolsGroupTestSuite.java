package com.sevice;

import com.domain.ToolsGroup;
import com.exception.NotFoundException;
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

    private final ToolsGroup toolsGroup = ToolsGroup.builder()
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

    @Test
    public void testGetExistingGroup() throws NotFoundException {
        //Given
        ToolsGroup toolsGroup1 = new ToolsGroup();
        toolsGroup1.setName("ogrodnicze");
        //When
        service.saveGroup(toolsGroup1);
        long id = toolsGroup1.getId();
        ToolsGroup group = service.getGroup(id).orElseThrow(NotFoundException::new);

        //Then
        assertEquals(group.getName(), toolsGroup1.getName());
    }

    @Test(expected = NotFoundException.class)
    public void testTryToGetNotExistingGroup() throws NotFoundException {
        ToolsGroup group = service.getGroup(100).orElseThrow(NotFoundException::new);
    }
}
