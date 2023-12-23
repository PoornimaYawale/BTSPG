package com.project.findPg.ControllerTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.User;
import com.project.findPg.controller.BookingController;
import com.project.findPg.services.BookingService;
import com.project.findPg.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookingController bookingController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddBooking() throws Exception {
        int userId = 1;
        int roomId = 1;
        Booking booking = new Booking();

        when(bookingService.addBooking(any(Booking.class), eq(userId), eq(roomId))).thenReturn(booking);

        mockMvc.perform(post("/api/booking/add/{userId}/{roomId}", userId, roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").exists())
                .andExpect(jsonPath("$.bookingId").value(booking.getBookingId()));

        verify(bookingService).addBooking(any(Booking.class), eq(userId), eq(roomId));
    }

    @Test
    public void testGetBookings() throws Exception {
        List<Booking> bookings = new ArrayList<>();

        when(bookingService.getBookings()).thenReturn(bookings);

        mockMvc.perform(get("/api/booking/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(bookings.size()));

        verify(bookingService).getBookings();
    }

    @Test
    public void testGetBooking() throws Exception {
        int bookingId = 1;
        Booking booking = new Booking();

        when(bookingService.getBooking(bookingId)).thenReturn(booking);

        mockMvc.perform(get("/api/booking/getById/{bookingId}", bookingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").exists())
                .andExpect(jsonPath("$.bookingId").value(booking.getBookingId()));

        verify(bookingService).getBooking(bookingId);
    }

    @Test
    public void testDeleteBooking() throws Exception {
        int bookingId = 1;

        doNothing().when(bookingService).deleteBooking(bookingId);

        mockMvc.perform(delete("/api/booking/deleteById/{bookingId}", bookingId))
                .andExpect(status().isOk());

        verify(bookingService).deleteBooking(bookingId);
    }


    @Test
    public void testGetBookingByUser() throws Exception {
        int userId = 1;
        List<Booking> bookings = new ArrayList<>();

        when(userService.getUser(userId)).thenReturn(new User());
        when(bookingService.findBookingByUser(any(User.class))).thenReturn(bookings);

        mockMvc.perform(get("/api/booking/getbyUserId/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(bookings.size()));

        verify(userService).getUser(userId);
        verify(bookingService).findBookingByUser(any(User.class));
    }
}
