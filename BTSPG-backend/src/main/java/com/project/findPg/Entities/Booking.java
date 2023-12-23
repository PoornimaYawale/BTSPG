package com.project.findPg.Entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.project.findPg.Entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.findPg.Entities.Room;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "bookingId")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	@NotNull
	private int noOfWeeks;
	private float totalPrice;
	private boolean bookingStatus = false;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name= "userId")
//	@JsonBackReference 
	private User user;
	
	@NotNull
	@OneToOne
	@JoinColumn(name= "roomId")
//	@JsonBackReference
	private Room room;
	
//	@OneToOne(mappedBy ="booking")
////	@JsonManagedReference
//	private Payment payment;

}
