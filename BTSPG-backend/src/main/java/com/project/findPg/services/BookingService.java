package com.project.findPg.services;

import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.User;

import java.util.List;

public interface BookingService {
	
	Booking addBooking(Booking booking, int userId,int roomId);
	List<Booking> getBookings();
	Booking getBooking(int bookingId);
	void deleteBooking(int bookingId);
    Booking updateBooking(Booking booking, int bookingId);	
//    List<Booking > getBookingsByUserId(int userId);
    

	List<Booking> findBookingByNoOfWeeks(int noOfWeeks);
	List<Booking> findBookingByUser(User user);
}
