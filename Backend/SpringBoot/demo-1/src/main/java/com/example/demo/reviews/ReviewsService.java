package com.example.demo.reviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.orderhistory.OrderHistoryRepo;
import com.example.demo.user.Users;
/**
 * @author SB_3
 */
@Service
public class ReviewsService {
	

	
	@Autowired
	private ReviewsRepo reviewsRepo;
	/**
	 * queries and returns for all reviews 
	 * @return all reviews
	 */
	public List<Reviews> getAllReviews() {
		List<Reviews> orders = new ArrayList<>();

		reviewsRepo.findAll().forEach(orders::add);
		return orders;
	}
	/**
	 * returns all reviews of given username for reviewee
	 * @param username reviewee name
	 * @return list of reviews of reviewee
	 */
	public List<Reviews> getReviewsByReviewee(String username) {
		List<Reviews> reviews = reviewsRepo.findByRevieweeUsername(username);
		return reviews;
	}
	/**
	 * returns all reviews of given username for reviewer
	 * @param username reviewer name
	 * @return list of reviews 
	 */
	public List<Reviews> getReviewsByReviewer(String username) {
		List<Reviews> reviews = reviewsRepo.findByReviewerUsername(username);
		return reviews;
	}
	/**
	 * return certain review by id
	 * @param id idetifier of review
	 * @return review 
	 */
	public Reviews getReviewById(int id) {
		Optional<Reviews> review = reviewsRepo.findById(id);
		
		return review.get();
	}
	/**
	 * add a review to the reviews table
	 * @param review review wanting to be added
	 */
	public void addReviews(Reviews review) {
		reviewsRepo.save(review);
	}
	/**
	 * update current review in table 
	 * @param id id of review
	 * @param newReview review you want to update it with
	 */
	public void updateReview(int id, Reviews newReview) {
		Optional<Reviews> u = reviewsRepo.findById(id);
		Reviews update = u.get();
		update = newReview;
		reviewsRepo.save(update);
	}

	/**
	 * Deletes specified review from database
	 * @param id Id of Review object to be deleted from database
	 */
	public void deleteReview(int id) {
		reviewsRepo.deleteById(id);		
	}

}
