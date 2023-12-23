package com.project.findPg.ServiceTests;

import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.Room;
import com.project.findPg.Entities.User;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Repositories.BookingRepository;
import com.project.findPg.Repositories.RoomRepository;
import com.project.findPg.services.BookingService;
import com.project.findPg.services.BookingServiceImpl;
import com.project.findPg.services.RoomService;
import com.project.findPg.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingTests {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserService userService;

    @Mock
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAddBooking() {
        int userId = 1;
        int roomId = 1;
        int noOfWeeks = 2;
        
        User user = new User();
        user.setUserId(userId);
        
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomPrice(100.0f);
        room.setRoomStatus(false);
        
        Booking booking = new Booking();
        booking.setNoOfWeeks(noOfWeeks);
        booking.setBookingStatus(true);
        
        
        when(userService.getUser(userId)).thenReturn(user);
        when(roomService.getRoom(roomId)).thenReturn(room);
        
        float expectedPrice = room.getRoomPrice() * noOfWeeks;
        
        Booking expectedBooking = new Booking();
        expectedBooking.setNoOfWeeks(noOfWeeks);
        expectedBooking.setTotalPrice(expectedPrice);
        expectedBooking.setUser(user);
        expectedBooking.setRoom(room);
        
        when(bookingRepository.save(any(Booking.class))).thenReturn(expectedBooking);
        when(roomRepository.save(any(Room.class))).thenReturn(room);
        Booking result = bookingService.addBooking(booking, userId, roomId);
        
        assertEquals(expectedBooking, result);
        assertTrue(room.isRoomStatus());
        verify(userService, times(1)).getUser(userId);
        verify(roomService, times(1)).getRoom(roomId);
        verify(bookingRepository, times(1)).save(any(Booking.class));
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    void testGetBookings_withExistingBookings() {
        // Arrange
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        bookings.add(new Booking());
        bookings.add(new Booking());

        when(bookingRepository.findAll()).thenReturn(bookings);

        // Act
        List<Booking> result = bookingService.getBookings();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());

        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testGetBookings_withNoBookingsThrowEmptyListException() {
        // Arrange
        when(bookingRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(EmptyListException.class, () -> bookingService.getBookings());

        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testGetBooking() {
        // Arrange
        int bookingId = 1;
        Booking booking = new Booking();
        booking.setBookingId(bookingId);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        // Act
        Booking result = bookingService.getBooking(bookingId);

        // Assert
        assertNotNull(result);
        assertEquals(bookingId, result.getBookingId());

        verify(bookingRepository, times(1)).findById(bookingId);
    }

  
    
    @Test
    void testDeleteBooking() {
        // Arrange
        int bookingId = 1;
        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setNoOfWeeks(2);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        // Act
        bookingService.deleteBooking(bookingId);

        verify(bookingRepository, times(1)).delete(booking);
    }

  

    @Test
    void testUpdateBooking() {
        // Arrange
        int bookingId = 1;
        Booking existingBooking = new Booking();
        existingBooking.setBookingId(bookingId);

        Booking updatedBooking = new Booking();
        updatedBooking.setNoOfWeeks(3);
        updatedBooking.setTotalPrice(300.0f);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));
        when(bookingRepository.save(existingBooking)).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Booking result = bookingService.updateBooking(updatedBooking, bookingId);

        // Assert
        assertNotNull(result);
        assertEquals(bookingId, result.getBookingId());
        assertEquals(3, result.getNoOfWeeks());
        assertEquals(300.0, result.getTotalPrice());

        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, times(1)).save(existingBooking);
    }

   
    @Test
    void testFindBookingByNoOfWeeks() {
        // Arrange
        int noOfWeeks = 2;
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        bookings.add(new Booking());

        when(bookingRepository.findBookingByNoOfWeeks(noOfWeeks)).thenReturn(bookings);

        // Act
        List<Booking> result = bookingService.findBookingByNoOfWeeks(noOfWeeks);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(bookingRepository, times(1)).findBookingByNoOfWeeks(noOfWeeks);
    }

   

    @Test
    void testFindBookingByUser() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        bookings.add(new Booking());

        when(bookingRepository.findBookingByUser(user)).thenReturn(bookings);

        // Act
        List<Booking> result = bookingService.findBookingByUser(user);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(bookingRepository, times(1)).findBookingByUser(user);
    }
}
