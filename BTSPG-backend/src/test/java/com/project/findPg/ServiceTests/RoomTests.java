package com.project.findPg.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.findPg.Entities.Room;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyInputFieldException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Repositories.RoomRepository;
import com.project.findPg.services.RoomServiceImpl;

class RoomTests {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRoom() {
        // Arrange
        Room room = new Room();
        room.setRoomDescription("Spacious room");
        room.setCapacity(2);
        room.setAddress("123 Street");
        room.setCity("City");
        room.setImage("room.jpg");
        room.setRoomPrice(100.0f);

        when(roomRepository.save(any(Room.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Room result = roomService.addRoom(room);

        // Assert
        assertNotNull(result);
        assertEquals(room, result);

        verify(roomRepository, times(1)).save(any(Room.class));
    }

   
    @Test
    void testGetRooms() {
        // Arrange
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());
        rooms.add(new Room());

        when(roomRepository.findAll()).thenReturn(rooms);

        // Act
        List<Room> result = roomService.getRooms();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testGetRoomsThrowEmptyListException() {
        // Arrange
        when(roomRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(EmptyListException.class, () -> roomService.getRooms());

        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void testGetRoom() {
        // Arrange
        int roomId = 1;
        Room room = new Room();
        room.setRoomId(roomId);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // Act
        Room result = roomService.getRoom(roomId);

        // Assert
        assertNotNull(result);
        assertEquals(room, result);

        verify(roomRepository, times(1)).findById(roomId);
    }

   

    @Test
    void testDeleteRoom() {
        // Arrange
        int roomId = 1;
        Room room = new Room();
        room.setRoomId(roomId);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // Act
        roomService.deleteRoom(roomId);

        // Assert
        verify(roomRepository, times(1)).delete(room);
    }

   
    @Test
    void testUpdateRoom() {
        // Arrange
        int roomId = 1;
        Room existingRoom = new Room();
        existingRoom.setRoomId(roomId);

        Room updatedRoom = new Room();
        updatedRoom.setRoomDescription("Updated room");

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(existingRoom));
        when(roomRepository.save(any(Room.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Room result = roomService.updateRoom(updatedRoom, roomId);

        // Assert
        assertNotNull(result);
        assertEquals(updatedRoom.getRoomDescription(), result.getRoomDescription());

        verify(roomRepository, times(1)).findById(roomId);
        verify(roomRepository, times(1)).save(any(Room.class));
    }

  

    @Test
    void testFindRoomByCity() {
        // Arrange
        String city = "City";
        Room room = new Room();

        when(roomRepository.findRoomByCity(city)).thenReturn(room);

        // Act
        Room result = roomService.findRoomByCity(city);

        // Assert
        assertNotNull(result);
        assertEquals(room, result);

        verify(roomRepository, times(1)).findRoomByCity(city);
    }

 

    @Test
    void testFindRoomByRoomPriceLessThan() {
        // Arrange
        float roomPrice = 100.0f;
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());
        rooms.add(new Room());

        when(roomRepository.findRoomByRoomPriceLessThan(roomPrice)).thenReturn(rooms);

        // Act
        List<Room> result = roomService.findRoomByRoomPriceLessThan(roomPrice);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(roomRepository, times(1)).findRoomByRoomPriceLessThan(roomPrice);
    }
}
