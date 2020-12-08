package com.controller;

import com.domain.Tool;
import com.domain.ToolsGroup;
import com.domain.User;
import com.dto.BookingDto;
import com.google.gson.Gson;
import com.repository.ToolRepository;
import com.repository.ToolsGroupRepository;
import com.repository.UserRepository;
import com.resourcesData.BookingDtoCreator;
import com.resourcesData.UserCreator;
import org.junit.Before;
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
@WebMvcTest(BookingController.class)
public class BookingControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingController controller;


    private UserRepository userRep;
    private ToolRepository toolRep;
    private ToolsGroupRepository groupRepository;


    private ToolsGroup toolsGroup;
    private Tool tool;
    private User user;

    private BookingDto bookingDto;


    @Before
    public void init() {
        toolsGroup = new ToolsGroup();
        toolsGroup.setName("ogrodnicze");
        groupRepository.save(toolsGroup);

        tool = new Tool();
        tool.setGroupId(toolsGroup);
        tool.setName("miotła");
        tool.setProducer("Fiskars");
        tool.setModel("XTB23");
        toolRep.save(tool);

        user = new User();
        user.setName("Jan");
        user.setSurname("Niezb");
        user.setEmail("Jan@g.com");
        user.setPhone("1111111112");
        user.setPesel("854002552");
        user.setPassword("pasoswrd");
        userRep.save(user);

        bookingDto = BookingDtoCreator.bookingDtoCreator();
        bookingDto.setUserId(user.getId());
        bookingDto.setToolId(tool.getId());
    }

    @Test
    public void schouldFetchEmptyListOfBookings() throws Exception {
        //Given
        List<BookingDto> bookingDtos = new ArrayList<>();
        when(controller.getAllBookings()).thenReturn(bookingDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/bookings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void schouldFetchNotEmptyListOfBookings() throws Exception {
        //Given
        List<BookingDto> bookingDtos = new ArrayList<>();
        bookingDtos.add(bookingDto);
        when(controller.getAllBookings()).thenReturn(bookingDtos);

        //When & Then
        mockMvc.perform(get("/api/v1/bookings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].user", is(bookingDto.getUserId())))
                .andExpect(jsonPath("$[0].bookedDateFrom", is(bookingDto.getBookedDateFrom())))
                .andExpect(jsonPath("$[0].tool", is(bookingDto.getToolId())));
    }

    @Test
    public void shouldGetBookingWithIndicatedId() throws Exception {
        //Given
        long id = bookingDto.getId();
        when(controller.getBooking(id)).thenReturn(bookingDto);

        //When & Then
        mockMvc.perform(get("/api/v1/bookings/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tool", is(bookingDto.getToolId())))
                .andExpect(jsonPath("$.user", is(bookingDto.getUserId())))
                .andExpect(jsonPath("$.bookedDateTo", is(bookingDto.getBookedDateTo())));
    }

    @Test
    public void shouldDeleteBooking() throws Exception {
        //Given
        BookingDto bookingDto = BookingDtoCreator.bookingDtoCreator();
        List<BookingDto> bookingDtos = new ArrayList<>();
        bookingDtos.add(bookingDto);
        when(controller.getAllBookings()).thenReturn(bookingDtos);

        //When & Then
        mockMvc.perform(delete("/api/v1/bookings/" + bookingDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void schouldUpdateBooking() throws Exception {
        //Given
        BookingDto bookingDto = BookingDtoCreator.bookingDtoCreator();
        List<BookingDto> bookingDtos = new ArrayList<>();
        bookingDtos.add(bookingDto);
        BookingDto updatedBookingDto = BookingDtoCreator.updatedBookingDtoCreator();
        when(controller.updateBookingById(ArgumentMatchers.anyLong(), (ArgumentMatchers.any(BookingDto.class)))).thenReturn(updatedBookingDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedBookingDto);

        //When & Then
        mockMvc.perform(put("/api/v1/bookings/" + bookingDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
//                .andExpect(jsonPath("$.id", is(87)))
//                .andExpect(jsonPath("$.user", is(updatedBookingDto.getUserId())))
                .andExpect(jsonPath("$.bookedDateFrom", is(updatedBookingDto.getBookedDateFrom())));
    }

    @Test
    public void shouldCreateBooking() throws Exception {
        //Given
        BookingDto bookingDto = BookingDtoCreator.bookingDtoCreator();
        when(controller.createBooking(ArgumentMatchers.any(BookingDto.class))).thenReturn(bookingDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookingDto);

        //When & Then
        mockMvc.perform(post("/api/v1/bookings/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user", is(bookingDto.getUserId())))
                .andExpect(jsonPath("$.bookedDateFrom", is(bookingDto.getBookedDateFrom())));
    }

    @Test
    public void shouldNotCreateBookingBecauseToolWithIdAlreadyExists() throws Exception {
        BookingDto bookingDto = BookingDtoCreator.bookingDtoCreator();
        bookingDto.setId(1L);

        when(controller.createBooking(ArgumentMatchers.any(BookingDto.class))).thenReturn(bookingDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookingDto);


        mockMvc.perform(post("/api/v1/ecommercee/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isNotFound());
    }
}
