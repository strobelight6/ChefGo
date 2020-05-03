
package com.example.demo.user;




import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.allergies.Allergies;
import com.example.demo.orderhistory.OrderHistory;



/**
 * @author SB_3
 *
 */
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @Column(name = "username")
    @Size(max = 50)
    @NotNull
    /**
     * Unique username for User
     */
    private String username;
 
    @Column(name = "email")
    /*
     * Email of User
     */
    private String email;
    
    @Column(name = "name")
    @Size(max = 30)
    /*
     * Name of User
     */
    private String name;
     
    @Column(name = "password")
    @Size(max = 30)
    /*
     * Password for User
     */
    private String password;
    
    @Column(name = "userType")
    /*
     * Type of User
     * 0 - admin
     * 1 - customer
     * 2 - chef
     */
    private Integer userType;
    
    @Column(name = "rating")
    /*
     * Rating out of 5 for User
     */
    private Double rating;
    
    
    @Column(name = "address")
    @Size(max = 40)
    /*
     * Address of User
     */
    private String address;
    
    @Column(name = "state")
    @Size(max = 20)
    /*
     * State that User lives in
     */
    private String state;
    
    @Column (name = "zip")
    /*
     * Zip code of User
     */
    private Integer zip;

    
    /*
     * Empty constructor
     */
    public Users() {
    	
    }
    /**
     * Creates User object with given parameters
     * @param username Unique username
     * @param email Email
     * @param name Name
     * @param pass Password
     * @param rating Rating out of 5
     * @param type User type
     * @param address Street Address
     * @param state State
     * @param zip Zip Code
     */    
    public Users(String username, String email, String name, String pass, Double rating,
    		Integer type, String address, String state, Integer zip) {
    	this.username = username;
    	this.email = email;
    	this.name= name;
    	this.password = pass;
    	this.userType = type;
    	this.rating = rating;
    	this.address = address;
    	this.state = state;
    	this.zip = zip;
    }
    /**
     * Returns the type of User
     * 0 - admin
     * 1- customer
     * 2 - chef    
     * @return Type of User
     */
    public Integer getUserType() {
    	return this.userType;
    }
    /**
     * Sets the User's type
     * @param user Type for User
     */
    public void setUserType(Integer user) {
    	this.userType = user;
    }
    /**
     * Returns the name of User
     * @return Name of User
     */
    public String getName() {
    	return this.name;
    }
    /**
     * Sets the name of the User
     * @param name Desired name for User
     */
    public void setName(String name) {
    	this.name = name;
    }
    /**
     * Returns the unique username for User
     * @return Username of User
     */
    public String getUsername() {
    	return this.username;
    }
    /**
     * Sets the username of User
     * @param name Unique username for User
     */
    public void setUsername(String name) {
    	this.username = name;
    }
    /**
     * Returns the password for User
     * @return Password for User
     */
    public String getPassword() {
    	return this.password;
    }
    /**
     * Sets the password for User
     * @param pass Password for User
     */
    public void setPassword(String pass) {
    	this.password = pass;
    }
    /**
     * Returns the rating out of 5 for User
     * @return Rating for User
     */
    public Double getRating() {
    	return this.rating;
    }
    /**
     * Sets the rating for User
     * @param r Rating out of 5 for User
     */
    public void setRating(Double r) {
    	this.rating = r;
    }
    /**
     * Returns the street address of User
     * @return Street address of User
     */
    public String getAddress() {
    	return this.address;
    }
    /**
     * Sets the street address for User
     * @param address Street address for User 
     */
    public void setAddress(String address) {
    	this.address = address;
    }
    /**
     * Returns the of State that User lives in
     * @return State that User lives in
     */
    public String getState() {
    	return this.state;
    }
    /**
     * Sets the state that User lives in
     * @param state State that User lives in
     */
    public void setState(String state) {
    	this.state = state;
    }
    /**
     * Sets the zip code for User
     * @param zip Zip code
     */
    public void setZip(int zip) {
    	this.zip = zip;
    }
//    public void setCity(String city) {
//    	this.city = city;
//    }
    /**
     * Returns zip code of User
     * @return Zip code
     */
    public int getZip() {
    	return this.zip;
    }
//    public String getCity() {
//    	return this.city;
//    }
    /**
     * Returns the email of User
     * @return Email of User
     */
    public String getEmail() {
    	return this.email;
    }
    /**
     * Sets the email of User
     * @param email Email of User
     */
    public void setEmail(String email) {
    	this.email = email;
    }
    
//    public void addOrder(OrderHistory order) {
//    	order.setCustomer(this);
//    	this.orders.add(order);
//    }
//    
//    public List<OrderHistory> getOrders(){
//    	return this.orders;
//    }
//    
//    public List<Allergies> getAllergies(){
//    	return this.allergies;
//    }
//    
//    public void addAllergy(Allergies allergy) {
//    	allergies.add(allergy);
//    }
    
    
}
