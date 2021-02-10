package com.controller;

import com.dto.ToolDto;
import com.google.gson.Gson;
import com.resourcesData.ToolDtoCreator;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToolController.class)
public class ToolControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolController controller;


    @Test
    public void schouldFetchEmptyListOfTools() throws Exception {
        //Given
        List<ToolDto> toolDtos = new ArrayList<>();
        when(controller.getAllTools()).thenReturn(toolDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/tools").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfTools() throws Exception {
        //Given
        ToolDto toolDto = ToolDtoCreator.toolDtoCreator();
        List<ToolDto> toolDtos = new ArrayList<>();
        toolDtos.add(toolDto);
        when(controller.getAllTools()).thenReturn(toolDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/tools").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(toolDto.getName())))
                .andExpect(jsonPath("$[0].model", is(toolDto.getModelId())))
                .andExpect(jsonPath("$[0].groupId", is(9)));
    }

    @Test
    public void shouldGetToolWithIndicatedId() throws Exception {
        //Given
        ToolDto toolDto = ToolDtoCreator.toolDtoCreator();
        long id = toolDto.getId();
        when(controller.getTool(id)).thenReturn(toolDto);

        //When & Then
        mockMvc.perform(get("/api/v1/tools/"+toolDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(toolDto.getName())))
                .andExpect(jsonPath("$.producer", is(toolDto.getProducerId())))
                .andExpect(jsonPath("$.model", is(toolDto.getModelId())));
    }

    @Test
    public void shouldDeleteTool() throws Exception {
        //Given
        ToolDto toolDto = ToolDtoCreator.toolDtoCreator();
        List<ToolDto> toolDtos = new ArrayList<>();
        toolDtos.add(toolDto);
        when(controller.getAllTools()).thenReturn(toolDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/tools/" + toolDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateTool() throws Exception {
        //Given
        ToolDto toolDto = ToolDtoCreator.toolDtoCreator();
        List<ToolDto> toolDtos = new ArrayList<>();
        toolDtos.add(toolDto);
        ToolDto updatedToolDto = ToolDtoCreator.updatedToolDtoCreator();
        when(controller.updateToolById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(ToolDto.class)))).thenReturn(updatedToolDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedToolDto);

        //When & Then
        mockMvc.perform(put("/api/v1/tools/"+toolDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
//                .andExpect(jsonPath("$.id", is(87)))
                .andExpect(jsonPath("$.name", is(updatedToolDto.getName())))
                .andExpect(jsonPath("$.model", is(updatedToolDto.getModelId())));
    }

    @Test
    public void shouldCreateTool() throws Exception {
        //Given
        ToolDto toolDto = ToolDtoCreator.toolDtoCreator();
        when(controller.createTool(ArgumentMatchers.any(ToolDto.class))).thenReturn(toolDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(toolDto);


        //When & Then
        mockMvc.perform(post("/api/v1/tools")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(toolDto.getName())))
                .andExpect(jsonPath("$.producer", is(toolDto.getProducerId())));
    }

    @Test
    public void shouldNotCreateToolBecauseToolWithIdAlreadyExists() throws Exception {
        ToolDto toolDto = ToolDtoCreator.toolDtoCreator();
        toolDto.setId(1L);

        when(controller.createTool(ArgumentMatchers.any(ToolDto.class))).thenReturn(toolDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(toolDto);


        mockMvc.perform(post("/api/v1/ecommercee/tools")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isNotFound());
    }
}
