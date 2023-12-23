package com.project.findPg.services;

import java.util.List;
import java.util.Optional;

import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.Room;
import com.project.findPg.Entities.User;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Repositories.BookingRepository;
import com.project.findPg.Repositories.RoomRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	UserService userService;

	@Autowired
	RoomService roomService;

	@Autowired
	RoomRepository roomRepository;
	

	@Override
	public Booking addBooking(Booking booking, int userId, int roomId) {

		User user = userService.getUser(userId);
		Room room = roomService.getRoom(roomId);

		float price = room.getRoomPrice() * booking.getNoOfWeeks();

		Booking createBooking = new Booking();

		createBooking.setNoOfWeeks(booking.getNoOfWeeks());
		createBooking.setTotalPrice(price);
		createBooking.setUser(user);
		createBooking.setRoom(room);

		if(booking.isBookingStatus()==true) {
			room.setRoomStatus(true);
		}
		
		roomRepository.save(room);

		return bookingRepository.save(createBooking);
	}

	@Override
	public List<Booking> getBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		if (bookings.isEmpty()) {
			throw new EmptyListException("Booking list is empty .make some bookings first");
		} else {
			return bookings;
		}
	}

	@Override
	public Booking getBooking(int bookingId) {

		Optional<Booking> booking = bookingRepository.findById(bookingId);
		if (booking.isPresent()) {
			return booking.get();
		}
		throw new DetailNotFoundException("booking doesnt exist in the DB for id",bookingId);

	}

	@Override
	public void deleteBooking(int bookingId) {
		Booking booking = getBooking(bookingId);
		bookingRepository.delete(booking);
	}

	@Override
	public Booking updateBooking(Booking booking, int bookingId) {

		Booking updatedBooking = getBooking(bookingId);
		updatedBooking.setNoOfWeeks(booking.getNoOfWeeks());
		updatedBooking.setTotalPrice(booking.getTotalPrice());

		return bookingRepository.save(updatedBooking);
	}

	@Override
	public List<Booking> findBookingByNoOfWeeks(int noOfWeeks) {
		List<Booking>bookings = bookingRepository.findBookingByNoOfWeeks(noOfWeeks);
		if(bookings.isEmpty()) {
			throw new EmptyListException("No Bookings Available for" +noOfWeeks+ "duration.");
		}
		return bookings;
	}

	@Override
	public  List<Booking> findBookingByUser(User user){
		List<Booking> bookings = bookingRepository.findBookingByUser(user);
		return bookings;
	}
	

}
