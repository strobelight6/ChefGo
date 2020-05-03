package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}
	
	public void addUser(User user) {
		userRepo.save(user);
	}
	
	public void updateUsername(int id, String username) {
		Optional<User> u = userRepo.findById(id);
		User update = u.get();
		update.setUsername(username);
		userRepo.save(update);
			
	}

	public void deleteUser(int id) {
		userRepo.deleteById(id);
		
	}
	
	
}
