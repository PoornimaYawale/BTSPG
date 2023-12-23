package com.project.findPg.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

	List<Booking> findBookingByNoOfWeeks(int noOfWeeks);

	List<Booking>  findBookingByUser(User user);
}
