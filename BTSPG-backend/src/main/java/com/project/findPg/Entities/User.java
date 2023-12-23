package com.project.findPg.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "userId")
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotNull
	@Size(min=4,message = "username must be of 4 characters!!")
	private String userName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull
	@Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).{8,20})",message = "password must be of 8 including number,uppercase ,lowercase and special characters!")
	private String password;
	
	@Email(message = "email is not valid !!")
	@Column(unique = true)
	@NotNull
	private String email;

	@NotNull
    private String role= "USER";
	
//	@OneToOne(mappedBy = "user")
//    private Room room;
//	
//	@OneToMany(mappedBy ="user")
//	//@JsonManagedReference
//	private List<Booking> booking;
	
	
//	@OneToMany(mappedBy ="user")
//	//@JsonManagedReference
//	private List<Payment> payments;
//	
//	@OneToMany(mappedBy ="user")
//	//@JsonManagedReference
//	private List<Review> reviews;

}
