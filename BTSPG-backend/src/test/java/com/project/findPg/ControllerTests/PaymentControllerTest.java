package com.project.findPg.ControllerTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.findPg.Entities.Payment;
import com.project.findPg.controller.PaymentController;
import com.project.findPg.services.PaymentService;

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

public class PaymentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddPayment() throws Exception {
        int userId = 1;
        int bookingId = 1;
        Payment payment = new Payment();

        when(paymentService.addPayment(any(Payment.class), eq(userId), eq(bookingId))).thenReturn(payment);

        mockMvc.perform(post("/api/payment/addPayment/{userId}/{bookingId}", userId, bookingId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentId").exists())
                .andExpect(jsonPath("$.paymentId").value(payment.getPaymentId()));

        verify(paymentService).addPayment(any(Payment.class), eq(userId), eq(bookingId));
    }

    @Test
    public void testGetPayments() throws Exception {
        List<Payment> payments = new ArrayList<>();

        when(paymentService.getPayments()).thenReturn(payments);

        mockMvc.perform(get("/api/payment/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(payments.size()));

        verify(paymentService).getPayments();
    }

    @Test
    public void testGetPayment() throws Exception {
        int paymentId = 1;
        Payment payment = new Payment();

        when(paymentService.getPayment(paymentId)).thenReturn(payment);

        mockMvc.perform(get("/api/payment/getById/{paymentId}", paymentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentId").exists())
                .andExpect(jsonPath("$.paymentId").value(payment.getPaymentId()));

        verify(paymentService).getPayment(paymentId);
    }

    @Test
    public void testDeletePayment() throws Exception {
        int paymentId = 1;

        doNothing().when(paymentService).deletePayment(paymentId);

        mockMvc.perform(delete("/api/payment/deleteById/{paymentId}", paymentId))
                .andExpect(status().isOk());

        verify(paymentService).deletePayment(paymentId);
    }

    @Test
    public void testFindByPaymentMethod() throws Exception {
        String paymentMethod = "Credit Card";
        List<Payment> payments = new ArrayList<>();

        when(paymentService.findByPaymentMethod(paymentMethod)).thenReturn(payments);

        mockMvc.perform(get("/api/payment/getByMethod/{paymentMethod}", paymentMethod))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(payments.size()));

        verify(paymentService).findByPaymentMethod(paymentMethod);
    }
}
