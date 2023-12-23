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
import com.project.findPg.Entities.Review;
import com.project.findPg.Entities.Room;
import com.project.findPg.Entities.User;
import com.project.findPg.services.ReviewService;
import com.project.findPg.services.RoomService;

@RestController
@RequestMapping(path="/api/review")
@CrossOrigin
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	RoomService roomService;
	
	@PostMapping("/add/{userId}/{roomId}")
	public Review addReview(@RequestBody Review review,@PathVariable int userId,@PathVariable int roomId) {
		return reviewService.addReview(review,userId,roomId);
	}

	@GetMapping("/getAll")
	public List<Review> getReviews() {
	
		return reviewService.getReviews();
	}

	@GetMapping("/getById/{reviewId}")
	public Review getReview(@PathVariable int  reviewId) {
		
		return reviewService.getReview(reviewId);
	}
	
	@DeleteMapping("/deleteById/{reviewId}")
	public void deleteReview(@PathVariable int reviewId) {
	   reviewService.deleteReview(reviewId);		
	}
	
	@PutMapping("/update/{reviewId}")
	public Review updateReview(@PathVariable int reviewId,@RequestBody Review review) {
		review.setReviewId(reviewId);
		return reviewService.updateReview(review,reviewId);
	}
	
	@GetMapping("/getByRating/{rating}")
	public List<Review> getReviewByRating(@PathVariable int rating) {
		
		return reviewService.findReviewByRating(rating);
	}

	@GetMapping("/getbyRoomId/{roomId}")
	public List<Review> getReviewByRoom(@PathVariable("roomId") int roomId){
		Room room = roomService.getRoom(roomId);
		return reviewService.findReviewByRoom(room);
		
	}
}
