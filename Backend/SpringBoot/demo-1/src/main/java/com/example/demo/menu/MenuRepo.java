package com.example.demo.menu;
/**
 * @author SB_3
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface MenuRepo extends JpaRepository<Menu, Integer>{
	
	public List<Menu> findByChefUsername(String username);
	public Menu findByTitle(String title);
}
