package com.example.demo.orderhistory;
/**
 * @author SB_3
 */
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.reviews.Reviews;
import com.example.demo.user.Users;

@RestController
public class OrderHistoryController {
	@Autowired
	private OrderHistoryService orderHistory;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	/**
	 * Endpoint for returning all Orders
	 * @return List of all Order objects
	 */
	@RequestMapping("/orderHistory")
	public List<OrderHistory> displayAllOrders() {
		return orderHistory.getAllOrders();
	}
	
	/**
	 * Endpoint for assigning/updating Chef to Order
	 * @param chef User that will be assigned to Chef
	 * @param oid Unique id of Order to be assigned
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/orderHistory/updateChef/{oid}")
	public void updateOrderChef(@RequestBody Users chef, @PathVariable int oid) {
		OrderHistory order = orderHistory.getOrderByOid(oid);
		order.setChef(chef);
		orderHistory.addOrderToHistory(order);
		makeOrderInactive(order);
	}
	
	/**
	 * Endpoint for returning all Orders that are still active
	 * @return List of all active Orders
	 */
	@RequestMapping("/orderHistory/active")
	public List<OrderHistory> displayActiveOrders() {
		return orderHistory.getOrdersByIsActiveAndWithoutChef();

	}
	/**
	 * Endpoint for returning all active Orders for a specific Chef
	 * @param username Username of Chef
	 * @return List of all active Orders for specified Chef
	 */
	@RequestMapping("/orderHistory/activeChef/{username}")
	public List<OrderHistory> displayActiveOrdersByChef(@PathVariable String username) {
		return orderHistory.getOrderByIsActiveAndChefName(username);
	}
	/**
	 * Endpoint for returning all active Orders for a specific Customer
	 * @param username Username of Customer
	 * @return List of all active Orders for specified Customer
	 */
	@RequestMapping("/orderHistory/activeCust/{username}")
	public List<OrderHistory> displayActiveOrdersByCustomer(@PathVariable String username) {
		return orderHistory.getOrderByIsActiveAndCustomerName(username);
	}
	/**
	 * Endpoint for adding Order to DB
	 * @param order Order object to be added to DB 
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/orderHistory")
	public void addOrder(@RequestBody OrderHistory order) {
		orderHistory.addOrderToHistory(order);
	}
	/**
	 * Endpoint for returning the most recent order of a specific User
	 * @param username Username of User
	 * @return Most recent Order object for specified User
	 */
	@RequestMapping("/orderHistory/recent/{username}")
	public List<OrderHistory> getMostRecentOrder(@PathVariable String username) {
		return orderHistory.getMostRecentOrder(username);
	}
	/**
	 * Endpoint for adding a Review to an Order
	 * @param review Review object to be added to Order
	 * @param oid Id for Order that is being reviewed
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/orderHistory/review/{oid}")
	public void addReview(@RequestBody Reviews review, @PathVariable Integer oid) {
		orderHistory.addReview(oid, review);
	}
	/**
	 * Endpoint for getting all Order objects for a specific Chef
	 * @param username Username of Chef user
	 * @return List of all Order objects assigned to a specified User
	 */
	@RequestMapping("/order/{username}")
	public List<OrderHistory> getOrderByChef(@PathVariable String username) {
		return orderHistory.getOrderByChefName(username);
	}
	
	/**
	 * Endpoint for getting all Order objects for a specific Customer
	 * @param username Username of user
	 * @return	List of all Order objects where specified user is assigned to Customer
	 */
	@RequestMapping("/orderHistory/cust/{username}")
	public List<OrderHistory> getAllOrdersByCust(@PathVariable String username) {
		return orderHistory.getOrderByCustName(username);
	}
	/**
	 * Endpoint for returning a specific Order object
	 * @param id Id of Order that is desired
	 * @return Order object with specified id if it exists
	 */
	@RequestMapping("/orderHistory/{id}")
	public OrderHistory displayOrderById(@PathVariable int id) {
		return orderHistory.getOrderByOid(id);
	}

	/**
	 * Endpoint for deleting an Order object from DB
	 * @param id Id of Order object to be deleted
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public void deleteOrder(@PathVariable Integer id) {
		orderHistory.deleteOrder(id);
	}
	
	/**
	 * Endpoint to obtain the User assigned to the Chef field of a specific Order object
	 * @param oid Id of Order object from which Chef will fetched
	 * @return ArrayList containing the single User object that is assigned to the Chef field of the ORder 
	 */
	@RequestMapping("/orderHistory/{oid}/chef")
	public List<Users> getChefForOrder(@PathVariable Integer oid) {
		return orderHistory.getChefByOid(oid);
	}
	
	
	

	/**
	 * Helper method to make Order objects inactive after a certain amount of time
	 * since a Chef has accepted the order 
	 * @param order Order object that will be set to inactive after 8 hours
	 */
	private void makeOrderInactive(OrderHistory order) {
		final Runnable setInactive = new Runnable() {
			public void run() {
				order.setActive(0);
				orderHistory.updateIsActive(order.getoid(), 0);
			}
		};
		
		scheduler.schedule(setInactive, 8, TimeUnit.HOURS);
	}
}
