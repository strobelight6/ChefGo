package com.example.demo.orderhistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.Users;




public interface OrderHistoryRepo extends JpaRepository<OrderHistory, Integer> {

	public List<OrderHistory> findByChefUsername(String username);
	public List<OrderHistory> findTopByCustomerUsernameOrderByOrderDateDesc(String username);
	public List<OrderHistory> findByIsActiveAndChef(int isActive, Users user);
	public Optional<OrderHistory> findByOid(int oid);
	public List<OrderHistory> findByIsActive(int isActive);
	public List<OrderHistory> findByIsActiveAndChefUsername(int isActive, String username);
	public List<OrderHistory> findByIsActiveAndCustomerUsername(int isActive, String username);
	public List<OrderHistory> findByCustomerUsername(String username);
}

