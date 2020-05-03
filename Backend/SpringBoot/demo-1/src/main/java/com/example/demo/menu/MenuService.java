package com.example.demo.menu;
/**
 * @author SB_3
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.UserRepo;
import com.example.demo.user.Users;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepo menuRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	/**
	 * Returns all Menu objects
	 * @return List of all Menu objects
	 */
	public List<Menu> getAllMenus() {
		List<Menu> menus = new ArrayList<>();

		menuRepo.findAll().forEach(menus::add);
		return menus;
	}
	
	/**
	 * Returns all Menu objects for a specific Chef
	 * @param username Desired Chef's username
	 * @return List of Menu objects for given Chef
	 */
	public List<Menu> getAllMenusByChef(String username) {
		List<Menu> menus = new ArrayList<>();
		
		menus = menuRepo.findByChefUsername(username);
		return menus;
	}
	
	/**
	 * Return Menu object with given title
	 * @param title Title of Menu object that is desired
	 * @return Menu object with given title if it exists
	 */
	public Menu getMenuByTitle(String title) {
		return menuRepo.findByTitle(title);
	}
	
	/**
	 * Adds given Menu to DB
	 * @param menu Menu object that will be added to DB
	 */
	public void addMenu(Menu menu) {
		menuRepo.save(menu);
	}
	
	/**
	 * Updates the title of a Menu
	 * @param menu Menu object that will have title changed
	 * @param title New title for the Menu
	 */
	public void updateMenu(String title, Menu menu) {
		Menu update = menuRepo.findByTitle(title);
		update = menu;
		menuRepo.save(update);
	}
	
	/**
	 * Deletes Menu with given title from DB
	 * @param title Title of Menu object to be deleted
	 */
	public void deleteMenu(String title) {
		Menu toDelete = menuRepo.findByTitle(title);
		menuRepo.delete(toDelete);
	}
	
	
	
	
	
	

}
