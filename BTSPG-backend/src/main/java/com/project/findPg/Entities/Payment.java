package com.project.findPg.Entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "paymentId")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	
	@NotNull
	private String paymentMethod;
	
	@NotNull
	private LocalDate paymentDate;
	
	private boolean paymentStatus = true;
	
	private float amount;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "userId")
	//@JsonBackReference
	private User user;

	@NotNull
	@OneToOne
	@JoinColumn(name="bookingId")
	//@JsonBackReference
	private Booking booking;

}
