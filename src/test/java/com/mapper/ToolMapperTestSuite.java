package com.mapper;

import com.domain.*;
import com.dto.ToolDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolMapperTestSuite {

    @Autowired
    private ToolMapper mapper;

    private ToolsGroup group = ToolsGroup.builder()
            .id(6L)
            .name("budowlane")
            .build();

    private Tool tool = Tool.builder()
            .id(2L)
            .name("wiertarka")
            .producer(Producer.builder().id(2).name("Bosch").build())
            .model(Model.builder().id(3).name("CV14J7").build())
            .group(group)
            .rentRate(BigDecimal.ONE)
            .location(Location.builder().id(4L).build())
            .build();

    private ToolDto toolDto = ToolDto.builder()
            .id(8L)
            .name("młot pneumatyczny")
            .producerId(6)
            .modelId(3)
            .build();

    @Test
    public void mapToTool() {
        //Given

        //When
        Tool tool = mapper.mapToTool(toolDto);


        //Then
        assertNotNull(tool);
        assertEquals(8L, tool.getId());
        assertEquals("młot pneumatyczny", tool.getName());
        assertEquals("L12", tool.getModel());
    }

    @Test
    public void mapToToolDto() {
        //Given

        //When
        ToolDto toolDto = mapper.mapToToolDto(tool);

        //Then
        assertNotNull(toolDto);
        assertEquals(2L, toolDto.getId());
        assertEquals(6, toolDto.getProducerId());
        assertEquals("wiertarka", toolDto.getName());

    }

    @Test
    public void mapToToolDtoList() {
        //Given

        List<Tool> tools = new ArrayList<>();
        tools.add(tool);

        //When
        List<ToolDto> usersDtoList = mapper.mapToToolDtoList(tools);

        //Then
        assertNotNull(usersDtoList);
        assertEquals(1, usersDtoList.size());
        assertEquals("wiertarka", usersDtoList.get(0).getName());
    }
}
