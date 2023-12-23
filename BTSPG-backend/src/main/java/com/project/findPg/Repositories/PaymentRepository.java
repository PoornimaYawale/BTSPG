package com.project.findPg.Repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.findPg.Entities.Payment;
import com.project.findPg.Entities.User;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	List<Payment> findByPaymentMethod(String paymentMethod);
	List<Payment> findPaymentByUser(User user);
}
