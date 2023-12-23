package com.project.findPg.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.Review;
import com.project.findPg.Entities.Room;
import com.project.findPg.Entities.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	List<Review> findReviewByRating(int rating);
	
	List<Review>  findReviewByRoom(Room room);
}