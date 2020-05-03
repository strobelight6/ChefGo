package com.example.demo.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;



@Entity
@Table(name = "Demo2")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "cook")
    private boolean cook;
    
    public User() {
    	id = null;
    	username = null;
    	phone = null;
    	cook = false;
    }
    
    public User(int id, String name, String phone, boolean cook) {
    	this.id = id;
    	this.username = name;
    	this.phone = phone;
    	this.cook = cook;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPhone() {
        return this.phone;
    }

    public void setTelephone(String phone) {
        this.phone = phone;
    }
    
    public boolean getCook() {
    	return this.cook;
    }
    
    public void setCook(boolean cook) {
    	this.cook = cook;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }

}
