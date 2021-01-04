package com.mapper;

import com.domain.ToolsGroup;
import com.dto.ToolsGroupDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupMapperTestSuite {

    @Autowired
    public ToolsGroupMapper mapper;

    private ToolsGroup toolsGroup = ToolsGroup.builder()
            .id(5L)
            .name("budowlane")
            .build();

    private ToolsGroupDto toolsGroupDto = ToolsGroupDto.builder()
            .id(37L)
            .name("ogrodnicze")
            .build();

    @Test
    public void mapToGroup() {
        //Given

        //When
        ToolsGroup toolsGroup = mapper.mapToToolsGroup(toolsGroupDto);


        //Then
        assertNotNull(toolsGroup);
        assertEquals(37L, toolsGroup.getId());
        assertEquals("ogrodnicze", toolsGroup.getName());
    }

    @Test
    public void mapToGroupDto() {
        //Given

        //When
        ToolsGroupDto toolsGroupDto = mapper.mapToToolsGroupDto(toolsGroup);

        //Then
        assertNotNull(toolsGroupDto);
        assertEquals(5L, toolsGroupDto.getId());
        assertEquals("budowlane", toolsGroupDto.getName());


    }

    @Test
    public void mapToUserDtoList() {
        //Given

        List<ToolsGroup> toolsGroups = new ArrayList<>();
        toolsGroups.add(toolsGroup);

        //When
        List<ToolsGroupDto> groupsDtoList = mapper.mapToToolsGroupDtoList(toolsGroups);

        //Then
        assertNotNull(groupsDtoList);
        assertEquals(1, groupsDtoList.size());
        assertEquals("budowlane", groupsDtoList.get(0).getName());
    }
}
