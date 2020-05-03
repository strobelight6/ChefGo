package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.orderhistory.OrderHistory;
import com.example.demo.orderhistory.OrderHistoryService;

@SpringBootApplication
public class Demo1Application {


	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}
	

}
