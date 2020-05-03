package com.example.demo.menu;
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

import com.example.demo.user.UserService;

@RestController
public class MenuController {

	@Autowired
	MenuService menuService;

	/**
	 * Endpoint for getting all Menu objects
	 * @return List of all Menu objects
	 */
	@RequestMapping("/menus")
	public List<Menu> getAllMenus(){
		List<Menu> menus = menuService.getAllMenus();
		return menus;
	}
	
	/**
	 * Endpoint for getting all Menu objects for a given Chef's username
	 * @param username Chef's username
	 * @return List of Menu objects for given Chef's username
	 */
	@RequestMapping("/menus/chef/{username}")
	public List<Menu> getAllMenusByChef(@PathVariable String username){
		List<Menu> menus = menuService.getAllMenusByChef(username);
		return menus;		
	}
	/**
	 * Endpoint for getting Menu with given title
	 * @param title Title of Menu object that is desired
	 * @return Menu object with given title if it exists
	 */
	@RequestMapping("/menus/{title}")
	public Menu getMenuByTitle(@PathVariable String title) {
		return menuService.getMenuByTitle(title);
	}
	/**
	 * Endpoint for adding a Menu object to DB
	 * @param menu Menu object to be added to DB
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/menus")
	public void addMenu(@RequestBody Menu menu) {
		menuService.addMenu(menu);
	}
	/**
	 * Endpoint for updating the title of a Menu
	 * @param menu Menu object that will have title changed
	 * @param title New title for the Menu
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/menus/{title}")
	public void updateMenu(@RequestBody Menu menu, @PathVariable String title) {
		menuService.updateMenu(title, menu);
	}
	
	/**
	 * Endpoint for deleting a Menu object from DB
	 * @param title Title of Menu object to be deleted
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/menus/{title}")
	public void deleteMenu(@PathVariable String title) {
		menuService.deleteMenu(title);
	}
}
