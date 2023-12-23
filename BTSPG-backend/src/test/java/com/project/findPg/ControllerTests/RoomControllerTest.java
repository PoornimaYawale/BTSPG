package com.project.findPg.ControllerTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.findPg.Entities.Room;
import com.project.findPg.controller.RoomController;
import com.project.findPg.services.RoomService;

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

public class RoomControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddRoom() throws Exception {
        Room room = new Room();

        when(roomService.addRoom(any(Room.class))).thenReturn(room);

        mockMvc.perform(post("/api/room/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").exists())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()));

        verify(roomService).addRoom(any(Room.class));
    }

    @Test
    public void testGetRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();

        when(roomService.getRooms()).thenReturn(rooms);

        mockMvc.perform(get("/api/room/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(rooms.size()));

        verify(roomService).getRooms();
    }

    @Test
    public void testGetRoom() throws Exception {
        int roomId = 1;
        Room room = new Room();

        when(roomService.getRoom(roomId)).thenReturn(room);

        mockMvc.perform(get("/api/room/getById/{roomId}", roomId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").exists())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()));

        verify(roomService).getRoom(roomId);
    }

    @Test
    public void testFindRoomByCapacity() throws Exception {
        int capacity = 4;
        Room room = new Room();

        when(roomService.findRoomByCapacity(capacity)).thenReturn(room);

        mockMvc.perform(get("/api/room/getByCapacity/{capacity}", capacity))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").exists())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()));

        verify(roomService).findRoomByCapacity(capacity);
    }

    @Test
    public void testFindRoomByCity() throws Exception {
        String city = "New York";
        Room room = new Room();

        when(roomService.findRoomByCity(city)).thenReturn(room);

        mockMvc.perform(get("/api/room/getByCity/{city}", city))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").exists())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()));

        verify(roomService).findRoomByCity(city);
    }

    @Test
    public void testFindRoomByRoomPriceLesserThan() throws Exception {
        float roomPrice = 100.0f;
        List<Room> rooms = new ArrayList<>();

        when(roomService.findRoomByRoomPriceLessThan(roomPrice)).thenReturn(rooms);

        mockMvc.perform(get("/api/room/getByPriceRange/{roomPrice}", roomPrice))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(rooms.size()));

        verify(roomService).findRoomByRoomPriceLessThan(roomPrice);
    }

    @Test
    public void testDeleteRoom() throws Exception {
        int roomId = 1;

        doNothing().when(roomService).deleteRoom(roomId);

        mockMvc.perform(delete("/api/room/deleteById/{roomId}", roomId))
                .andExpect(status().isOk());

        verify(roomService).deleteRoom(roomId);
    }

    @Test
    public void testUpdateRoom() throws Exception {
        int roomId = 1;
        Room room = new Room();

        when(roomService.updateRoom(any(Room.class), eq(roomId))).thenReturn(room);

        mockMvc.perform(put("/api/room/update/{roomId}", roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").exists())
                .andExpect(jsonPath("$.roomId").value(room.getRoomId()));

        verify(roomService).updateRoom(any(Room.class), eq(roomId));
    }
}
