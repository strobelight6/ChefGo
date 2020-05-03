package com.example.demo.reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SB_3
 */

@RestController
public class ReviewsController {
	
	@Autowired
	private ReviewsService review; 
	/**
	 * returns all reviews
	 * @return list of reviews
	 */
	@RequestMapping("/reviews")
	public List<Reviews> displayAllReviews() {
		return review.getAllReviews();
	}
	/**
	 * returns a certain review by Id
	 * @param id identifier of review
	 * @return specfic review
	 */
	@RequestMapping("/reviews/{rid}")
	public Reviews displayOrderById(@PathVariable int id) {
		return review.getReviewById(id);
	}
	/**
	 * Endpoint to add a review to the table
	 * @param newReview Review object to be added to DB
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/reviews")
	public void addReview(@RequestBody Reviews newReview) {
		review.addReviews(newReview);
	}
	/**
	 * updates a review 
	 * @param rid Unique id of Review to be updated
	 * @param newReview New Review object that will replace current
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/reviews/{rid}")
	public void updateReview(@PathVariable Integer rid, @RequestBody Reviews newReview) {
		review.updateReview(rid, newReview);
	}
	/**
	 * get all reviews by a certain reviewee name
	 * @param username name of reviewee
	 * @return list of reviews for specified reviewee
	 */
	@RequestMapping("/reviewee/{username}")
	public List<Reviews> getReviewsByReviewee(@PathVariable String username) {
		return review.getReviewsByReviewee(username);
	}
	/**
	 * gets all reviews by a certain reviewer name
	 * @param username name of reviewer
	 * @return list of reviews of reviewer
	 */
	@RequestMapping("/reviewer/{username}")
	public List<Reviews> getReviewsByReviewer(@PathVariable String username) {
		return review.getReviewsByReviewer(username);
	}
}
