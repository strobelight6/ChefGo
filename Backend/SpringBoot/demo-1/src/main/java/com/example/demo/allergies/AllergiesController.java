package com.example.demo.allergies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.Users;
/**
 * @author SB_3
 */
@RestController
public class AllergiesController {
	
	@Autowired
	private AllergiesService allergies;
	/**
	 * queries a list of all allergies
	 * @return List of all Allergy objects
	 */
	@RequestMapping("/allergies")
	public List<Allergies> displayAllAllergies() {
		return allergies.getAllAllergies();
	}
	/**
	 * queries a list of allergies by username
	 * @param username Username of User that has allergies
	 * @return List of allergies for specified User
	 */
	@RequestMapping("/allergies/{username}")
	public List<Allergies> displayUserAllergies(@PathVariable String username){
		return allergies.getUserAllergies(username);
	}
	/**
	 * adds an allergy to the table 
	 * @param allergy Allergy to be added to DB
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/allergies")
	public void addAllergy(@RequestBody Allergies allergy) {
		allergies.addAllergy(allergy);
	}
	

}
