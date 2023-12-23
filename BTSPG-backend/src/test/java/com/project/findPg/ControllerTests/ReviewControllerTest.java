package com.project.findPg.ControllerTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.findPg.Entities.Review;
import com.project.findPg.Entities.Room;
import com.project.findPg.controller.ReviewController;
import com.project.findPg.services.ReviewService;
import com.project.findPg.services.RoomService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ReviewController reviewController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddReview() throws Exception {
        int userId = 1;
        int roomId = 1;
        Review review = new Review();

        when(reviewService.addReview(any(Review.class), eq(userId), eq(roomId))).thenReturn(review);

        mockMvc.perform(post("/api/review/add/{userId}/{roomId}", userId, roomId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewId").exists())
                .andExpect(jsonPath("$.reviewId").value(review.getReviewId()));

        verify(reviewService).addReview(any(Review.class), eq(userId), eq(roomId));
    }

    @Test
    public void testGetReviews() throws Exception {
        List<Review> reviews = new ArrayList<>();

        when(reviewService.getReviews()).thenReturn(reviews);

        mockMvc.perform(get("/api/review/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(reviews.size()));

        verify(reviewService).getReviews();
    }

    @Test
    public void testGetReview() throws Exception {
        int reviewId = 1;
        Review review = new Review();

        when(reviewService.getReview(reviewId)).thenReturn(review);

        mockMvc.perform(get("/api/review/getById/{reviewId}", reviewId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewId").exists())
                .andExpect(jsonPath("$.reviewId").value(review.getReviewId()));

        verify(reviewService).getReview(reviewId);
    }

    @Test
    public void testDeleteReview() throws Exception {
        int reviewId = 1;

        doNothing().when(reviewService).deleteReview(reviewId);

        mockMvc.perform(delete("/api/review/deleteById/{reviewId}", reviewId))
                .andExpect(status().isOk());

        verify(reviewService).deleteReview(reviewId);
    }

    @Test
    public void testUpdateReview() throws Exception {
        int reviewId = 1;
        Review review = new Review();

        when(reviewService.updateReview(any(Review.class), eq(reviewId))).thenReturn(review);

        mockMvc.perform(put("/api/review/update/{reviewId}", reviewId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewId").exists())
                .andExpect(jsonPath("$.reviewId").value(review.getReviewId()));

        verify(reviewService).updateReview(any(Review.class), eq(reviewId));
    }

    @Test
    public void testGetReviewByRating() throws Exception {
        int rating = 4;
        List<Review> reviews = new ArrayList<>();

        when(reviewService.findReviewByRating(rating)).thenReturn(reviews);

        mockMvc.perform(get("/api/review/getByRating/{rating}", rating))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(reviews.size()));

        verify(reviewService).findReviewByRating(rating);
    }

    @Test
    public void testGetReviewByRoom() throws Exception {
        int roomId = 1;
        List<Review> reviews = new ArrayList<>();
        Room room = new Room();

        when(roomService.getRoom(roomId)).thenReturn(room);
        when(reviewService.findReviewByRoom(room)).thenReturn(reviews);

        mockMvc.perform(get("/api/review/getbyRoomId/{roomId}", roomId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(reviews.size()));

        verify(roomService).getRoom(roomId);
        verify(reviewService).findReviewByRoom(room);
    }
}
