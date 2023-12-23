package com.project.findPg.services;

import com.project.findPg.Entities.Review;
import com.project.findPg.Entities.Room;

import java.util.List;

public interface ReviewService {
	
	Review addReview(Review review,int userId,int roomId);
	List<Review> getReviews();
	Review getReview(int reviewId);   
	void deleteReview(int reviewId);
	Review updateReview(Review review,int reviewId);
	
	List<Review> findReviewByRating(int rating);
	List<Review>  findReviewByRoom(Room room);
	//List<Review> findReviewByUserId(int UserId);
}
