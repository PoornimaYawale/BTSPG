package com.project.findPg.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.Payment;
import com.project.findPg.Entities.User;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Exception.PaymentNotSuccessfullException;
import com.project.findPg.Repositories.BookingRepository;
import com.project.findPg.Repositories.PaymentRepository;
import com.project.findPg.services.BookingService;
import com.project.findPg.services.PaymentServiceImpl;
import com.project.findPg.services.UserService;

class PaymentTests {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private UserService userService;

    @Mock
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPayment() {
        // Arrange
        int userId = 1;
        int bookingId = 1;

        User user = new User();
        user.setUserId(userId);

        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setTotalPrice(100.0f);

        Payment payment = new Payment();
        payment.setPaymentMethod("Credit Card");
        payment.setPaymentStatus(true);

        when(userService.getUser(userId)).thenReturn(user);
        when(bookingService.getBooking(bookingId)).thenReturn(booking);
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Payment result = paymentService.addPayment(payment, userId, bookingId);

        // Assert
        assertNotNull(result);
        assertEquals(booking, result.getBooking());
        assertEquals(user, result.getUser());
        assertEquals(100.0, result.getAmount());
        assertTrue(result.isPaymentStatus());

        verify(userService, times(1)).getUser(userId);
        verify(bookingService, times(1)).getBooking(bookingId);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPayment_ThrowPaymentNotSuccessfullException() {
        // Arrange
        int userId = 1;
        int bookingId = 1;

        User user = new User();
        user.setUserId(userId);

        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setTotalPrice(100.0f);

        Payment payment = new Payment();
        payment.setPaymentMethod("Credit Card");
        payment.setPaymentStatus(false);

        when(userService.getUser(userId)).thenReturn(user);
        when(bookingService.getBooking(bookingId)).thenReturn(booking);

        // Act & Assert
        assertThrows(PaymentNotSuccessfullException.class, () -> paymentService.addPayment(payment, userId, bookingId));

        verify(userService, times(1)).getUser(userId);
        verify(bookingService, times(1)).getBooking(bookingId);
        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testGetPayments() {
        // Arrange
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment());
        payments.add(new Payment());

        when(paymentRepository.findAll()).thenReturn(payments);

        // Act
        List<Payment> result = paymentService.getPayments();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(paymentRepository, times(1)).findAll();
    }


    @Test
    void testGetPayment() {
        // Arrange
        int paymentId = 1;
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

        // Act
        Payment result = paymentService.getPayment(paymentId);

        // Assert
        assertNotNull(result);
        assertEquals(payment, result);

        verify(paymentRepository, times(1)).findById(paymentId);
    }

 

    @Test
    void testDeletePayment() {
        // Arrange
        int paymentId = 1;

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(new Payment()));

        // Act
        paymentService.deletePayment(paymentId);

        // Assert
        verify(paymentRepository, times(1)).deleteById(paymentId);
    }

    @Test
    void testFindByPaymentMethod() {
        // Arrange
        String paymentMethod = "Credit Card";

        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment());
        payments.add(new Payment());

        when(paymentRepository.findByPaymentMethod(paymentMethod)).thenReturn(payments);

        // Act
        List<Payment> result = paymentService.findByPaymentMethod(paymentMethod);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(paymentRepository, times(1)).findByPaymentMethod(paymentMethod);
    }

}
