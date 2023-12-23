package com.project.findPg.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "roonId")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	
	@NotNull
	private String roomDescription;
	
	@NotNull
	private int capacity;
	
	@NotNull
	private String address;
	
	@NotNull
	private String city;
	
	@NotNull
	private String image;
	
	private boolean roomStatus = true;
	@NotNull
	private float roomPrice;
	
//	@OneToOne
//	@JoinColumn(name ="userId")
//	//@JsonBackReference
//	private User user;
	
//	@OneToOne(mappedBy ="room")
//	private Booking booking;
	
//	@OneToMany(mappedBy ="room")
////	@JsonManagedReference
//	private List<Review> reviews;
	
}
