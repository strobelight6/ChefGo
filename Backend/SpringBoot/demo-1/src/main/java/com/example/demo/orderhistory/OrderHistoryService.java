package com.example.demo.orderhistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.reviews.Reviews;
import com.example.demo.user.Users;

/**
 * 
 * @author SB_3
 *
 */
@Service
public class OrderHistoryService {

	
	@Autowired
	private OrderHistoryRepo orderHistoryRepo;
	/**
	 * Returns a list of all Order objects
	 * @return ArrayList of all Order objects
	 */
	public List<OrderHistory> getAllOrders() {
		List<OrderHistory> orders = new ArrayList<>();

		orderHistoryRepo.findAll().forEach(orders::add);
		return orders;
	}
	/**
	 * Return all Order objects by active or inactive
	 * @param isActive integer to specify whether active or inactive orders should be returned (0 or 1)
	 * @return a list of all orders where input value equals isActive variable of Order object
	 */
	public List<OrderHistory> getOrdersByIsActive(int isActive){
		List<OrderHistory> u = orderHistoryRepo.findByIsActive(isActive);
		return u;
	}
	/**
	 * Returns a list of all Orders that has specified username assigned to Chef variable
	 * @param username name of chef
	 * @return list of orders by chef username
	 */
	public List<OrderHistory> getOrderByChefName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByChefUsername(username);
		return u;
	}

	/**
	 * Returns all Orders that has specified username assigned to Customer variable
	 * @param username
	 * @return ArrayList containing all orders for specified Customer
	 */
	public List<OrderHistory> getOrderByCustName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByCustomerUsername(username);
		return u;
	}
	/**
	 * get active orders by a certain chef
	 * @param username name of chef
	 * @return list of active orders of specific chef
	 */
	public List<OrderHistory> getOrderByIsActiveAndChefName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByIsActiveAndChefUsername(1,username);
		return u;
	}
	/**
	 * gets active orders that haven't had a chef assigned to it yet
	 * @return a list of active orders waiting to be picked up
	 */
	public List<OrderHistory> getOrdersByIsActiveAndWithoutChef() {
		List<OrderHistory> u = orderHistoryRepo.findByIsActiveAndChef(1,null);
		return u;
	}
	
	/**
	 * finds active orders based on customer username
	 * @param username name of customer
	 * @return list of active orders of customer
	 */
	public List<OrderHistory> getOrderByIsActiveAndCustomerName(String username) {
		List<OrderHistory> u = orderHistoryRepo.findByIsActiveAndCustomerUsername(1,username);
		return u;
	}
	
	/**
	 * get order by id
	 * @param oid id of order
	 * @return Order object
	 */
	public OrderHistory getOrderByOid(int oid) {
		Optional<OrderHistory> u = orderHistoryRepo.findByOid(oid);
		
		return u.get();
	}
	
	/**
	 * gets most recent orders for a specific User  
	 * @param username name of customer
	 * @return list in descending order of orders
	 */
	public List<OrderHistory> getMostRecentOrder(String username) {
		return orderHistoryRepo.findTopByCustomerUsernameOrderByOrderDateDesc(username);
	}
	/**
	 * adds order to order table
	 * @param order Order object to be added to DB
	 */
	public void addOrderToHistory(OrderHistory order) {
		order.setActive(1);
		orderHistoryRepo.save(order);
	}
	/**
	 * updates the dish name for a specific order
	 * @param id id of order
	 * @param dish updated name of order
	 */
	public void updateDishName(int id, String dish) {
		Optional<OrderHistory> u = orderHistoryRepo.findById(id);
		OrderHistory update = u.get();
		update.setDish(dish);
		orderHistoryRepo.save(update);	
	}
	
	/**
	 * updates the isActive variable for an Order object
	 * @param id Id of Order object to update
	 * @param active Value to assign to isActive variable of Order object (should be 0 or 1)
	 */
	public void updateIsActive(int id, int active) {
		Optional<OrderHistory> u = orderHistoryRepo.findById(id);
		OrderHistory update = u.get();
		update.setActive(active);
		orderHistoryRepo.save(update);	
	}
	/**
	 * adds a review to a specific order
	 * @param id id of Order object
	 * @param review Review object to assign to specified Order object 
	 */
	public void addReview(int id, Reviews review) {
		Optional<OrderHistory> u = orderHistoryRepo.findById(id);
		OrderHistory update = u.get();
		update.setReview(review);
		update.setActive(0);
		orderHistoryRepo.save(update);	
	}
	/**
	 * deletes specific order off of order table
	 * @param oid id of order to delete
	 */
	public void deleteOrder(Integer oid) {
		Optional<OrderHistory> u = orderHistoryRepo.findByOid(oid);
		OrderHistory toDelete = u.get();
		orderHistoryRepo.delete(toDelete);
	}
	
	/**
	 * Returns ArrayList containing the one User that is assigned to the specified Order object
	 * @param oid Id of Order object to fetch Chef from
	 * @return List containing the one User assigned to Chef field of specified Order
	 */
	public List<Users> getChefByOid(int oid){
		Optional<OrderHistory> u = orderHistoryRepo.findByOid(oid);
		OrderHistory order = u.get();
		List<Users> chef = new ArrayList<Users>();
		chef.add(order.getChef());
		return chef;
	}
	
}
