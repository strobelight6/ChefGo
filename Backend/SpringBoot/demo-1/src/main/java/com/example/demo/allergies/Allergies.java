package com.example.demo.allergies;
/**
 * @author SB_3
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.demo.user.Users;

@Entity
@Table(name = "Allergies")
public class Allergies {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	
	@Column(name = "allergy")
	@Size(max = 40)
	private String allergy;
	
	@ManyToOne
	@JoinColumn(name = "userID")
	private Users user;
	

	/**
	 *  creates empty allergy
	 */
	public Allergies() {
		
	}
	/**
	 * constructor for allergy if given one
	 * @param id Unique id for Allergy
	 * @param allergy Allergy name
	 * @param user User that has allergy
	 */
	public Allergies(Integer id, String allergy, Users user) {
		this.allergy = allergy;
		this.id = id;
		this.user = user;
	}
	
	/**
	 * sets id of allergy
	 * @param id Unique id for Allergy
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * gets id of allergy
	 * @return Unique id for Allergy
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * gets allergy name
	 * @return Name of Allergy
	 */
	public String getAllergy() {
		return this.allergy;
	}
	/**
	 * set allergys name
	 * @param allergy Name of Allergy
	 */
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	/**
	 * gets user of that allergy
	 * @return User object that has Allergy
	 */
	public Users getUser() {
		return this.user;
}
	/**
	 * sets user of the allergy
	 * @param user User object that has Allergy
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
}
