package com.project.findPg.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.findPg.Entities.Payment;
import com.project.findPg.Entities.User;
import com.project.findPg.services.PaymentService;

@RestController
@RequestMapping(path = "/api/payment")
@CrossOrigin
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	//updated by aditya
	@PostMapping("/addPayment/{userId}/{bookingId}")
	public Payment addPayment(@PathVariable int userId,@PathVariable int bookingId,@RequestBody Payment payment) {
		return paymentService.addPayment(payment,userId,bookingId);	
	}

	@GetMapping("/getAll")
	public List<Payment> getPayments() {
		
		return paymentService.getPayments();
	}
	@GetMapping("/getbyUserId/{userId}")
	public List<Payment> getPaymentByUserId(@PathVariable int userId){
		return paymentService.getPaymentByUserId(userId);
	}

	@GetMapping("/getById/{paymentId}")
	public Payment getPayment(@PathVariable  int paymentId) {
		
		return paymentService.getPayment(paymentId);
	}

	@DeleteMapping("/deleteById/{paymentId}")
	public void deletePayment(@PathVariable int paymentId) {
		paymentService.deletePayment(paymentId);
		
	}
	
	@GetMapping("/getByMethod/{paymentMethod}")
	public List<Payment> findByPaymentMethod(@PathVariable String paymentMethod) {
		
		return paymentService.findByPaymentMethod(paymentMethod);
	}

}
