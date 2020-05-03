package com.example.demo.allergies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.Users;
/**
 * @author SB_3
 */
@Service
public class AllergiesService {

	@Autowired
	private AllergiesRepo allergiesRepo;
	
	/**
	 * gets all allergies
	 * @return List of all allergies
	 */
	public List<Allergies> getAllAllergies() {
		List<Allergies> allergies = new ArrayList<Allergies>();
		
		allergiesRepo.findAll().forEach(allergies :: add);
		return allergies;
	}
	/**
	 * gets all allergies by a username
	 * @param username Username of User
	 * @return all allergies of the user
	 */
	public List<Allergies> getUserAllergies(String username){
		List<Allergies> allergies = new ArrayList<Allergies>();
		
		allergiesRepo.findByUserUsername(username).forEach(allergies :: add);
		return allergies;
	}
	
	/**
	 * adds an allergy
	 * @param allergy Allergy to be added to DB
	 */
	public void addAllergy(Allergies allergy) {
		allergiesRepo.save(allergy);
	}
	/**
	 * assigns user to allergy
	 * @param allergy Allergy object  to have User assigned
	 * @param user User object to be assigned to Allergy
	 */
	public void assignUserToAllergy(Allergies allergy, Users user) {
		allergy.setUser(user);
		allergiesRepo.save(allergy);
		
	}
	
	
	
	
}
