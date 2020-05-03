package com.example.demo.user;
/**
 * @author SB_3
 */

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

	
	public Optional<Users> findByUsername(String username);
	public Optional<List<Users>> findByZipAndUserType(Integer zip, Integer userType);

}
