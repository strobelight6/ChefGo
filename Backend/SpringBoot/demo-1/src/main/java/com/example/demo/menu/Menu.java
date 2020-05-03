/**
 * @author SB_3
 */
package com.example.demo.menu;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.user.Users;

@Entity
@Table(name = "Menu")
public class Menu {
	
	@Id
	@NotNull
	@Column(name = "title")
	@Size(max = 50)
	/*
	 * Title of Menu
	 */
	private String title;
	
	@Column(name= "appetizer")
	@Size(max = 50)
	/*
	 * Name of appetizer 
	 */
	private String appetizer;
	
	@Column(name= "entree")
	@Size(max = 50)
	/*
	 * Name of entree
	 */
	private String entree;
	
	@Column(name= "dessert")
	@Size(max = 50)
	/*
	 * Name of dessert
	 */
	private String dessert;
	
	@Column(name = "cost")
	/*
	 * Total cost of meal
	 */
	private double cost;
	
	@Column(name = "mDescription")
	@Size(max =200)
	/*
	 * Description of this menu
	 */
	private String mDescription;
	
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "chefID")
    /*
     * User who will cook the meal
     */
	private Users chef;
	
 /**
  * Empty constructor   
  */
	public Menu() {
	
	}
	
	/**
	 * 
	 * @param title	nickname for menu
	 * @param appetizer	name of appetizer dish
	 * @param entree	name of entree dish
	 * @param dessert	name of dessert dish
	 * @param cost	cost of these dishes together
	 * @param desc	description of this meal
	 * @param chef	chef that will make this meal
	 * Constructor for Menu object
	 */
	public Menu(String title, String appetizer, String entree, String dessert, double cost, String desc, Users chef) {
		
		this.title = title;
		this.appetizer = appetizer == null ? "none" : appetizer;
		this.entree = entree == null ? "none" : entree;
		this.dessert = dessert == null ? "none" : dessert;
		this.cost = cost;
		this.mDescription = desc;
		this.chef = chef;
	
	}
	
	/**
	 * Returns name of appetizer for menu 
	 * @return Name of appetizer
	 */
	public String getAppetizer() {
		return this.appetizer;
	}
	/**
	 * Sets appetizer to the given string
	 * @param appetizer	name of appetizer for menu
	 */
	public void setAppetizer(String appetizer) {
		this.appetizer = appetizer;
	}
	/**
	 * Returns name of entree for menu
	 * @return name of entree for menu
	 */
	public String getEntree() {
		return this.entree;
	}
	/**
	 * Sets name of entree for menu
	 * @param entree name of entree for menu
	 */
	public void setEntree(String entree) {
		this.entree = entree;
	}
	/**
	 * Returns name of dessert for menu
	 * @return name of dessert for menu
	 */
	public String getDessert() {
		return this.dessert;
	}
	/**
	 * Sets the name of the dessert for menu
	 * @param dessert name of dessert
	 */
	public void setDessert(String dessert) {
		this.dessert = dessert;
	}
	/**
	 * Returns the cost of the menu
	 * @return cost of the menu
	 */
	public double getCost() {
		return this.cost;
	}
	/**
	 * Sets the cost of the menu
	 * @param cost cost in dollars of the menu
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * Returns description of the menu
	 * @return Description of the menu
	 */
	public String getDescription() {
		return this.mDescription;
	}
	/**
	 * Sets the description for this menu
	 * @param desc description for the menu
	 */
	public void setDescription(String desc) {
		this.mDescription = desc;
	}
	/**
	 * Returns the username of the user who is the Chef for this menu
	 * @return username of Chef
	 */
	public String getChef() {
		return this.chef.getUsername();
	}
	/**
	 * Sets Chef to the given User object
	 * @see Users
	 * @param chef User object that will be Chef 
	 */
	public void setChef(Users chef) {
		this.chef = chef;
	}
	/**
	 * Returns the title of this menu
	 * @return title of the menu
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets the title of this menu
	 * @param title desired title for the menu
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
