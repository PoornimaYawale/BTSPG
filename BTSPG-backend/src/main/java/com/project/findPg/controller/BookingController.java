package com.project.findPg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.User;
import com.project.findPg.services.BookingService;
import com.project.findPg.services.UserService;

@RestController
@RequestMapping(path ="/api/booking")
@CrossOrigin
public class BookingController {
	
	@Autowired
	 BookingService bookingService;
	@Autowired
	UserService userService;
	
	@PostMapping("/add/{userId}/{roomId}")
	public Booking addBooking(@RequestBody Booking booking,@PathVariable("userId") int userId, @PathVariable("roomId") int roomId) {
		return bookingService.addBooking(booking,userId,roomId);
	}

	@GetMapping("/getAll")
	public List<Booking> getBookings() {
		return  bookingService.getBookings();
	}

	@GetMapping("/getById/{bookingId}")
	public Booking getBooking(@PathVariable("bookingId")  int bookingId) {
		
		return bookingService.getBooking(bookingId);
	}

	@DeleteMapping("/deleteById/{bookingId}")
	public void deleteBooking(@PathVariable("bookingId") int bookingId) {
		bookingService.deleteBooking(bookingId);		
	}
	
	@PutMapping("/update/{bookingid}")
	public Booking updateBooking(@PathVariable ("bookingId")int bookingId,@RequestBody  Booking booking) {
		
		booking.setBookingId(bookingId);
		return bookingService.updateBooking(booking,bookingId);
	}

	@GetMapping("/getbyUserId/{userId}")
	public List<Booking> getBookingByUser(@PathVariable("userId") int userId){
		User user = userService.getUser(userId);
		return bookingService.findBookingByUser(user);
		
	}
}
