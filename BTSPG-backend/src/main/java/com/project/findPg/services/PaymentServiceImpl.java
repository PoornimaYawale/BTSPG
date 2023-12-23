package com.project.findPg.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.Payment;
import com.project.findPg.Entities.User;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Exception.PaymentNotSuccessfullException;
import com.project.findPg.Repositories.BookingRepository;
import com.project.findPg.Repositories.PaymentRepository;

import io.github.classgraph.ResourceList.ByteArrayConsumerThrowsIOException;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public Payment addPayment(Payment payment, int userId, int bookingId) {

		User user = userService.getUser(userId);
		Booking booking = bookingService.getBooking(bookingId);
		float amt = booking.getTotalPrice();
		Payment createdPayment = new Payment();
		createdPayment.setAmount(amt);
		createdPayment.setPaymentDate(payment.getPaymentDate());
		createdPayment.setPaymentMethod(payment.getPaymentMethod());
		createdPayment.setBooking(booking);
		createdPayment.setUser(user);

		// to create booking status true after payment is successfully done

		if(payment.isPaymentStatus()==false) {
			bookingService.deleteBooking(bookingId);
			throw new PaymentNotSuccessfullException();
		}
		booking.setBookingStatus(true);
		bookingRepository.save(booking);

		return paymentRepository.save(createdPayment);

	}

	@Override
	public List<Payment> getPayments() {
		List<Payment> payments = paymentRepository.findAll();
		if (payments.isEmpty()) {
			throw new EmptyListException("No payments record found in the DB");
		}
		return payments;
	}

	@Override
	public Payment getPayment(int paymentId) {
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		if (payment.isPresent()) {
			return payment.get();
		}
		throw new DetailNotFoundException("payment not exist for id",paymentId);
	}

	@Override
	public void deletePayment(int paymentId) {
		if (paymentRepository.findById(paymentId).isPresent()) {
			paymentRepository.deleteById(paymentId);
			return;
		}
		throw new DetailNotFoundException("payment not exist int the DB for id:", paymentId);

	}

	@Override
	public List<Payment> findByPaymentMethod(String paymentMethod) {
		
		List<Payment> payments = paymentRepository.findByPaymentMethod(paymentMethod);
		if(payments.isEmpty()) {
			throw new EmptyListException("Payment doesnt exist in the DB for method :"+ paymentMethod);
		}
		return payments;
	}

	@Override
	public List<Payment> getPaymentByUserId(int userId) {
		User user = userService.getUser(userId);
		List<Payment> payments = paymentRepository.findPaymentByUser(user);
		if(payments.isEmpty()) {
			throw new EmptyListException("Payment doesnt exist in the DB for method :"+ userId);
		}
		return payments;
	}

	

}
