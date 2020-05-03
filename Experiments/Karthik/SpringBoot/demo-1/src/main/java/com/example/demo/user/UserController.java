package com.example.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService users;
	
	@RequestMapping("/users")
	public List<User> displayUsers() {
		return users.getAllUsers();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/users")
	public void addUser(@RequestBody User user) {
		users.addUser(user);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/users/{id}")
	public void updateUsername(@PathVariable int id, @RequestBody String username) {
		users.updateUsername(id, username);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		users.deleteUser(id);
	}
	
}
