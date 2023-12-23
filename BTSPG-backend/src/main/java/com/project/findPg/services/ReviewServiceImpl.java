package com.project.findPg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.findPg.Entities.Booking;
import com.project.findPg.Entities.Review;
import com.project.findPg.Entities.Room;
import com.project.findPg.Entities.User;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	UserService userService;

	@Autowired
	RoomService roomService;

	@Override
	public Review addReview(Review review, int userId, int roomId) {

		User user = userService.getUser(userId);
		Room room = roomService.getRoom(roomId);

		Review newReview = new Review();
		newReview.setComment(review.getComment());
		newReview.setRating(review.getRating());
		newReview.setUser(user); 
		newReview.setRoom(room);

		return reviewRepository.save(newReview);
	}

	@Override
	public List<Review> getReviews() {
		List<Review> review = reviewRepository.findAll();
		if(review.isEmpty()) {
			throw new EmptyListException("No reviews found in the DB , add some reviews.");
		}else{
			return review;
		}
	}

	@Override
	public Review getReview(int reviewId) {

		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new DetailNotFoundException("Review is not found in the DB for id" , reviewId));

		return review;
	}

	@Override
	public void deleteReview(int reviewId) {
		
		Review review = getReview(reviewId);
		reviewRepository.delete(review);

//		if (reviewRepository.findById(reviewId).isPresent()) {
//			reviewRepository.deleteById(reviewId);
//		}
//		else {
//		throw new RuntimeException("Room is not found for id" + reviewId);
//		}
	}

	@Override
	public Review updateReview(Review review, int reviewId) {

		Review updatedReview = getReview(reviewId);

		updatedReview.setComment(review.getComment());
		updatedReview.setRating(review.getRating());

		return reviewRepository.save(updatedReview);
	}

	@Override
	public List<Review> findReviewByRating(int rating) {
		
		return reviewRepository.findReviewByRating(rating);

	}
//
//	@Override
//	public List<Review> findReviewByUserId(int UserId) {
//		
//		return reviewRepository.findReviewByUserId(UserId);
//	}

	@Override
	public List<Review> findReviewByRoom(Room room) {
		List<Review> reviews = reviewRepository.findReviewByRoom(room);
		return reviews;
	}
}
