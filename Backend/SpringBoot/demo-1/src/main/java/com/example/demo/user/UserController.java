package com.example.demo.user;
/**
 * @author SB_3
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.orderhistory.OrderHistory;


@RestController
public class UserController {

	@Autowired
	private UserService users;
	/**
	 * Endpoint for returning all Users
	 * @return List of all User objects
	 */
	@RequestMapping("/users")
	public List<Users> displayAllUsers() {
		return users.getAllUsers();
	}
	 /**
	  * Endpoint for adding User object to DB
	  * @param user User object to be added to DB
	  */
	@RequestMapping(method = RequestMethod.POST, path = "/users")
	public void addUser(@RequestBody Users user) {
		users.addUser(user);
	}
	
	/**
	 * 
	 * Return all chefs within the specified zip code
	 * @param zip code from which Chefs will be obtained
	 * @return ArrayList of Users of Chef type within specified zip code
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/chefs/{zip}")
	public List<Users> getAllChefs(@PathVariable int zip){
		return users.getUsersByZip(zip);
	}	
	
	/**
	 * Endpoint for updating a users email
	 * @param username Username of User whose email will be updated
	 * @param email New email for User
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/users/{username}/email")
	public void updateUserEmail(@PathVariable String username, @RequestBody String email) {
		users.updateEmail(username, email);
	}

	
	/**
	 * Returns User with given username
	 * @param username Username of User
	 * @return User with given username
	 */
	@RequestMapping( path = "/user/{username}")
	public Users findbyUserName(@PathVariable String username) {
		return users.getUserByUsername(username);
		
	}
	/**
	 * Returns if user is in the database
	 * @param username username of User
	 * @return yes if user is in database no if user is not in database
	 */
	@RequestMapping( path = "/user/check/{username}")
	public String checkIfUserIsInDataBase(@PathVariable String username) {
		Users user =  users.getUserByUsername(username);
		
		if(user != null) {
			return "yes";
		}
		else {
			return "no";
		}
	}
	
	/**
	 * Endpoint for updating name of User
	 * @param username Username of User that needs to be changed
	 * @param fname New first name
	 * @param lname New last name
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/user/{username}/{fname}/{lname}")
	public void updatefName(@PathVariable String username, @PathVariable String fname, @PathVariable String lname) {
		String name = fname + " " + lname;
		users.updateName(username, name);
	}

	/**
	 * Endpoint for updating password of User
	 * @param username Username of User that needs to be changed
	 * @param password New password
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/users/{username}/password")
	public void updatePassword(@PathVariable String username, @RequestBody String password) {
		users.updatePassword(username, password);
	}
	
	/**
	 * Endpoint for changing type of User
	 * @param username Username of User that needs to be changed
	 * @param type New type for User
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/users/userType/{username}/{type}")
	public void updateUserType(@PathVariable String username, @PathVariable Integer type) {
		users.updateUserType(username, type);
	}
	
	/**
	 * Endpoint for changing address of User
	 * @param username Username of User that needs to be changed
	 * @param address New street address for User
	 * @param state New state User lives in
	 * @param zip New zip code for User
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/users/{username}/address")
	public void updateAddress(@PathVariable String username, @RequestBody String address, @RequestBody String state,
			@RequestBody Integer zip) {
		users.updateAddress(username, address, state, zip);
	}

	/**
	 * Endpoint for deleting User with given username
	 * @param username Username for User to be deleted
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/users/{username}")
	public void deleteUser(@PathVariable String username) {
		users.deleteUser(username);
	}
}
