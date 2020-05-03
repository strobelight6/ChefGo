package com.example.demo.reviews;

/**
 * @author SB_3
 */

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.user.Users;

@Entity
@Table(name = "Reviews")
public class Reviews {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    @NotNull 
    private Integer rid;
 
    @Column(name = "rating")
    @Digits(fraction = 1, integer = 1)
    private double rating;
    
    @Column(name = "rDescription")
    @Size(max = 300)
    private String rDescription;
    
    
    @Column(name = "reviewDate")
    private Date reviewDate;
    
    @ManyToOne
    @JoinColumn(name = "reviewerID")
    private Users reviewer;
    
    @ManyToOne
    @JoinColumn(name = "revieweeID")
    private Users reviewee;
    
    /**
     * Empty constructor
     */
    public Reviews() {
    	
    }
    /**
     * Creates Review object with given parameters
     * @param rid Unique id for Review
     * @param rating Rating of Review
     * @param rDescription Description for Review
     * @param rDate Date Review was made
     * @param reviewer User who was Customer
     * @param reviewee User who was Chef
     */
    public Reviews(Integer rid, double rating, String rDescription, Date rDate, Users reviewer, Users reviewee) {
    	this.rid =rid;
    	this.rating = rating;
    	this.rDescription = rDescription;
    	this.reviewDate = rDate;
    	this.reviewer= reviewer;
    	this.reviewee = reviewee;
    	
    }
    /**
     * gets rid of review
     * @return Unique id for Review
     */
    public Integer getrid() {
    	return this.rid;
    }
    /**
     * sets rid of review
     * @param rid Unique id for Review
     */
    public void setRid(Integer rid) {
    	this.rid = rid;
    }
    /**
     * gets rating of review
     * @return Rating of review
     */
    public double getRating() {
    	return this.rating;
    }
    /**
     * sets rating of review
     * @param rating Rating of review
     */
    public void setRating(double rating) {
    	this.rating = rating;
    }
    /**
     * gets description of review
     * @return Description for Review
     */
    public String getDescription() {
    	return this.rDescription;
    }
    /**
     * sets description of review
     * @param Description Description of Review
     */
    public void setDescription(String Description) {
    	this.rDescription= Description;
    }
    /**
     * gets date of review
     * @return Date Review object was made
     */
    public Date getDate() {
    	return this.reviewDate;
    }
    /**
     * sets date of review
     * @param date Date Review object was made
     */
    public void setDate(Date date) {
    	this.reviewDate = date;
    }
    /**
     * gets reviewer of review
     * @return Username of reviewer
     */
    public String getReviewer() {
    	return this.reviewer.getUsername();
    }
    /**
     * sets reviewer of review
     * @param reviewer User objects that was customer
     */
    public void setReviewer(Users reviewer) {
    	this.reviewer = reviewer;
    }
    /**
     * gets reviewee of review
     * @return Username of reviewee
     */
    public String getReviewee() {
    	return this.reviewee.getUsername();
    }
    /**
     * set reviewee of review
     * @param reviewee User object that was chef
     */
    public void setReviewee(Users reviewee) {
    	this.reviewee = reviewee;
    }

    
}
