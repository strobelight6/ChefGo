package com.example.demo.orderhistory;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.reviews.Reviews;
import com.example.demo.user.Users;
/**
 * 
 * @author SB_3
 *
 */
@Entity
@Table(name = "Order_History")
public class OrderHistory {


	/**
	 * Review object for Order
	 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reviewID")
    private Reviews review;
    
    /**
     * User object for Customer of ORder
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private Users customer;

    /**
     * Price for Order
     */
    @Column(name = "price")
    private Double price;

    /**
     * Date of Order
     */
    @Column(name = "orderDate")
    private Date orderDate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid")
    /**
     * Unique identifier for Object
     */
    private Integer oid;
    
    @Column(name= "dishName")
    @Size(max = 50)
    /**
     * Name of dish served
     */
    private String dishName;
    /**
     * Tells if Order is completed or not
     */
    @Column(name = "active")
    private Integer isActive;
    
    /**
     * User object that will cook the Order
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chefID")
    private Users chef;
    
    
    
    
    public OrderHistory() {
    }
    


    public OrderHistory(Integer oid, Date orderDate, Double price, Users chef, String dishName, Users customer) {
    	this.price = price;
    	this.oid = oid;
    	this.orderDate = orderDate;
    	this.dishName = dishName;
    	this.chef = chef;
    	this.customer = customer;
    	this.isActive = 1;
    	this.review = new Reviews();
    }
    
    /**
     * get review of order
     * @return review of order
     */
    public Reviews getReview() {
    	if(this.review != null) return this.review;
    	else return null;
    }
    /**
     * sets review of order
     * @param review review of order
     */
    public void setReview(Reviews review) {
    	this.review = review;
    }
    /**
     * gets id of order
     * @return id of order
     */
   public Integer getoid() {
    	return this.oid;
    }
   /**
    * sets id of order
    * @param oid id of order
    */
    public void setOid(Integer oid) {
    	this.oid = oid;
    }
    /**
     * gets price of order
     * @return price of order
     */
    public Double getPrice() {
    	return this.price;
    }
    /**
     * sets price of order
     * @param price new price of order
     */
    public void setPrice(Double price) {
    	this.price = price;
    }
    /**
     * gets dish name of order
     * @return dish name of order
     */
    public String getDish() {
    	return this.dishName;
    }
    /**
     * sets new dish name to dish
     * @param dish new dish name 
     */
    public void setDish(String dish) {
    	this.dishName= dish;
    }
    /**
     * gets date of order
     * @return date of order
     */
    public Date getDate() {
    	return this.orderDate;
    }
    /**
     * sets date of order
     * @param date date of order
     */
    public void setDate(Date date) {
    	this.orderDate = date;
    }
    /**
     * gets chef user 
     * @return user
     */
    public Users getChef() {
    	if(this.chef != null) return this.chef;
    	else return null;
    }
    /**
     * sets orders chef 
     * @param chef user waiting to be assigned
     */
    public void setChef(Users chef) {
    	this.chef = chef;
    }
    /**
     * gets orders customer
     * @return customer
     */
	public Users getCustomer() {
    	if(this.customer != null) return this.customer;
    	else return null;

	}
	/**
	 * sets orders customer field
	 * @param customer user 
	 */
	public void setCustomer(Users customer) {
		this.customer = customer;
	}
	/**
	 * gets status of order
	 * @return status  1 being active 0 being inactive
	 */
	public Integer getIsActive() {
		return this.isActive;
	}
	/**
	 * sets status of order
	 * @param isActive status of order 1 being active 0 being inactive
	 */
	public void setActive(Integer isActive) {
		this.isActive = isActive;
	}


    
    
}
