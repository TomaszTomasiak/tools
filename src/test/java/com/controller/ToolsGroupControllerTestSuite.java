package com.controller;

import com.dto.ToolsGroupDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToolsGroupController.class)
public class ToolsGroupControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolsGroupController controller;

    @Test
    public void schouldFetchEmptyListOfGroups() throws Exception {
        //Given
        List<ToolsGroupDto> dtos = new ArrayList<>();
        when(controller.getAllGroups()).thenReturn(dtos);

        //When & Then
        mockMvc.perform(get("/api/v1/groups").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfGroups() throws Exception {
        //Given
        ToolsGroupDto dto = new ToolsGroupDto();
        dto.setName("budowlane");
        List<ToolsGroupDto> dtos = new ArrayList<>();
        dtos.add(dto);

        when(controller.getAllGroups()).thenReturn(dtos);

        //When & Then
        mockMvc.perform(get("/api/v1/groups").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(dto.getName())));

    }

    @Test
    public void shouldGetGroupWithIndicatedId() throws Exception {
        //Given
        ToolsGroupDto dto = new ToolsGroupDto();
        dto.setId(8L);
        dto.setName("budowlane");
        long id = dto.getId();
        when(controller.getGroup(id)).thenReturn(dto);

        //When & Then
        mockMvc.perform(get("/api/v1/groups/" + dto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(dto.getName())));
    }


    @Test
    public void schouldUpdateGroup() throws Exception {
        //Given
        ToolsGroupDto dto = new ToolsGroupDto();
        dto.setName("budowlane");
        dto.setId(20L);

        ToolsGroupDto dtoUpdated = new ToolsGroupDto();
        dtoUpdated.setName("ogrodnicze");
        dtoUpdated.setId(20L);
        when(controller.updateGroupById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(ToolsGroupDto.class)))).thenReturn(dtoUpdated);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(dtoUpdated);

        //When & Then
        mockMvc.perform(put("/api/v1/groups/" + dto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
//                .andExpect(jsonPath("$.id", is(87)))
                .andExpect(jsonPath("$.name", is("ogrodnicze")));

    }

    @Test
    public void shouldCreateGroup() throws Exception {
        //Given
        ToolsGroupDto dto = new ToolsGroupDto();
        dto.setName("budowlane");
        when(controller.createGroup(ArgumentMatchers.any(ToolsGroupDto.class))).thenReturn(dto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(dto);


        //When & Then
        mockMvc.perform(post("/api/v1/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(dto.getName())));
    }

    @Test
    public void shouldNotCreateGroupBecauseGroupWithIdAlreadyExists() throws Exception {
        ToolsGroupDto dto = new ToolsGroupDto();
        dto.setId(1L);
        dto.setName("budowlane");


        when(controller.createGroup(ArgumentMatchers.any(ToolsGroupDto.class))).thenReturn(dto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(dto);


        mockMvc.perform(post("/api/v1/ecommercee/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isNotFound());
    }
}
