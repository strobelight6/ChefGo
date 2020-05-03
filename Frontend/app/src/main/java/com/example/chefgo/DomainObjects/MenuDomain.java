package com.example.chefgo.DomainObjects;

import java.util.HashMap;
import java.util.Map;

public class MenuDomain {
    /**
     * Title of Menu
     */
    private String title;


    /**
     * Name of appetizer
     */
    private String appetizer;


    /**
     * Name of entree
     */
    private String entree;


    /**
     * Name of dessert
     */
    private String dessert;


    /**
     * Total cost of meal
     */
    private double cost;


    /**
     * Description of this menu
     */
    private String mDescription;
/**
     * User who will cook the meal
     */
    private UsersDomain chef;

    /**
     * Empty constructor
     */
    public MenuDomain() {

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
    public MenuDomain(String title, String appetizer, String entree, String dessert, double cost, String desc, UsersDomain chef) {

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
     * @see UsersDomain
     * @param chef User object that will be Chef
     */
    public void setChef(UsersDomain chef) {
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
    /**
     *
     * @return json list of menu object
     */
    public Map<String, String> toJSON(){
        Map<String, String> menuMap = new HashMap<>();
        menuMap.put("title", getTitle());
        menuMap.put("appetizer", getAppetizer());
        menuMap.put("entree", getEntree());
        menuMap.put("dessert", getDessert());
        menuMap.put("description", getDescription());
        menuMap.put("cost", String.valueOf(getCost()));


        return menuMap;
    }
}
