package com.project.findPg.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.project.findPg.Entities.Payment;
import com.project.findPg.Entities.Review;

public interface PaymentService {
	Payment addPayment(Payment payment,int userId,int bookingId);
	List<Payment> getPayments();
	Payment getPayment(int paymentId);   
	void deletePayment(int paymentId);
	

	List<Payment> findByPaymentMethod(String paymentMethod);
	List<Payment> getPaymentByUserId(int userId);
	
}
