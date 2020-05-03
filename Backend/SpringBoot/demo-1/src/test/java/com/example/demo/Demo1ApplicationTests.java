package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.orderhistory.OrderHistoryController;
import com.example.demo.orderhistory.OrderHistoryService;
import com.example.demo.user.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Demo1Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Demo1ApplicationTests {

	

	
	@InjectMocks
	OrderHistoryController controller;
	@Mock
	OrderHistoryService service;
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}
	
	@Test
	public void testUpdateOrderChef() throws Exception {
		//Users chef = new Users("jstr", "asdfas", "karthik", "pass", 2.2 , 0, "way out west", "Iowa", "Ames");
		Users chef = new Users("jstr", "asdfas", "karthik", "pass", 2.2 , 2, "way out west", "Iowa", 50014);
		OrderHistory Order = new OrderHistory(1, new Date(100), 22.50, null, "chicken", null);

		
		Mockito.when(service.getOrderByOid(1)).thenReturn(Order);
		Mockito.doNothing().when(service).updateIsActive(1, 0);
		Mockito.doNothing().when(service).addOrderToHistory(Mockito.any(OrderHistory.class));

		ObjectMapper mapper = new ObjectMapper();
		String chefJSON = mapper.writeValueAsString(chef);
		
		mockMvc.perform(put("/orderHistory/updateChef/1").contentType(MediaType.APPLICATION_JSON).content(chefJSON));

		TimeUnit.MINUTES.sleep((long)1.5);
		assertEquals(1.0, Order.getIsActive(), 0.0);
		
	}
	
	@Test
	public void testActiveOrder() throws Exception {
		//Users chef = new Users("jstr", "asdfas", "karthik", "pass", 2.2 , 0, "way out west", "Iowa", "Ames");
		Users chef = new Users("jstr", "asdfas", "karthik", "pass", 2.2 , 0, "way out west", "Iowa", 50014);
		OrderHistory Order = new OrderHistory(1, new Date(100), 22.50, null, "chicken", null);
		
		Mockito.when(service.getOrderByOid(1)).thenReturn(Order);
		Mockito.doNothing().when(service).addOrderToHistory(Mockito.any(OrderHistory.class));
		
		controller.updateOrderChef(chef, 1);
		Mockito.verify(service).addOrderToHistory(Order);
		assertEquals(Order.getChef().getUsername(), chef.getUsername());
		
	}
	
	@Test
	public void testOrderByCustomer() throws Exception {
		//Users chef = new Users("jstr", "asdfas", "karthik", "pass", 2.2 , 0, "way out west", "Iowa", "Ames");
		Users user1 = new Users("jstr", "asdfas", "karthik", "pass", 2.2 , 0, "way out west", "Iowa", 50014);
		Users user2 = new Users("kp", "asdfas", "karthik", "pass", 2.2 , 0, "way out west", "Iowa", 50014);
		List<OrderHistory> orders = new ArrayList<OrderHistory>();
		OrderHistory Order1 = new OrderHistory(1, new Date(100), 22.50, null, "chicken", user1);
		OrderHistory Order2 = new OrderHistory(2, new Date(100), 22.50, null, "cheese", user2);
		orders.add(Order1);
		orders.add(Order2);
		
		Mockito.when(service.getOrderByCustName("jstr")).thenReturn(orders.subList(0, 0));
		List<OrderHistory> check;
		check = controller.getAllOrdersByCust("jstr");
		Mockito.verify(service).getOrderByCustName("jstr");
		assertEquals(orders.subList(0, 0), check);
		
	}



}
