package com.project.findPg.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.findPg.Entities.Review;
import com.project.findPg.Entities.Room;
import com.project.findPg.Entities.User;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Repositories.ReviewRepository;
import com.project.findPg.services.ReviewServiceImpl;
import com.project.findPg.services.RoomService;
import com.project.findPg.services.UserService;

class ReviewTests {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserService userService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReview() {
        
        int userId = 1;
        int roomId = 1;
        User user = new User();
        user.setUserId(userId);
        Room room = new Room();
        room.setRoomId(roomId);

        Review review = new Review();
        review.setComment("Great experience");
        review.setRating(5);
        review.setUser(user);
        review.setRoom(room);

        when(userService.getUser(userId)).thenReturn(user);
        when(roomService.getRoom(roomId)).thenReturn(room);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Act
        Review result = reviewService.addReview(review, userId, roomId);

        // Assert
        assertNotNull(result);
        assertEquals(review.getComment(), result.getComment());
        assertEquals(review.getRating(), result.getRating());
        assertEquals(user, result.getUser());
        assertEquals(room, result.getRoom());

        verify(userService, times(1)).getUser(userId);
        verify(roomService, times(1)).getRoom(roomId);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testGetReviews() {
        // Arrange
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());
        reviews.add(new Review());

        when(reviewRepository.findAll()).thenReturn(reviews);

        // Act
        List<Review> result = reviewService.getReviews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testGetReviewsThrowEmptyListException() {
        // Arrange
        when(reviewRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(EmptyListException.class, () -> reviewService.getReviews());

        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testGetReview() {
        // Arrange
        int reviewId = 1;
        Review review = new Review();
        review.setReviewId(reviewId);

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        // Act
        Review result = reviewService.getReview(reviewId);

        // Assert
        assertNotNull(result);
        assertEquals(review, result);

        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void testGetReviewThrowDetailNotFoundException() {
        // Arrange
        int reviewId = 1;

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DetailNotFoundException.class, () -> reviewService.getReview(reviewId));

        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void testDeleteReview() {
        // Arrange
        int reviewId = 1;
        Review review = new Review();
        review.setReviewId(reviewId);

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        // Act
        reviewService.deleteReview(reviewId);

        // Assert
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, times(1)).delete(review);
    }

  

    @Test
    void testUpdateReview() {
        // Arrange
        int reviewId = 1;
        Review existingReview = new Review();
        existingReview.setReviewId(reviewId);

        Review updatedReview = new Review();
        updatedReview.setReviewId(reviewId);
        updatedReview.setComment("Updated comment");
        updatedReview.setRating(4);

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(any(Review.class))).thenReturn(updatedReview);

        // Act
        Review result = reviewService.updateReview(updatedReview, reviewId);

        // Assert
        assertNotNull(result);
        assertEquals(updatedReview, result);

        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testFindReviewByRating() {
        // Arrange
        int rating = 5;
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());
        reviews.add(new Review());

        when(reviewRepository.findReviewByRating(rating)).thenReturn(reviews);

        // Act
        List<Review> result = reviewService.findReviewByRating(rating);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(reviewRepository, times(1)).findReviewByRating(rating);
    }

   
}
