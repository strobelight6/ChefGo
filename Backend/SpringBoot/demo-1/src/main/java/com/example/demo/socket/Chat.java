package com.example.demo.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.orderhistory.OrderHistoryService;
import com.example.demo.user.UserService;
import com.example.demo.user.Users;
import com.fasterxml.jackson.databind.ObjectMapper;


@ServerEndpoint(value = "/chat/{oid}/{username}")
@Component
public class Chat {

 	
    private static Map<String, Session> usernameSessionMap = new HashMap<>();
    private static Map<String, Integer> usernameOidMap = new HashMap<>();
    
	private static UserService userService;
	private static OrderHistoryService orderService;

	@Autowired
	public void setUsersService(UserService service) {
	      Chat.userService = service;
	}
	
	@Autowired
	public void setOrderService(OrderHistoryService orderService) {
		Chat.orderService = orderService;
	}
    
    private final Logger logger = LoggerFactory.getLogger(Chat.class);
    
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("oid") Integer oid) throws IOException 
    {
        logger.info("Entered into Open");
        
        usernameSessionMap.put(username, session);
        usernameOidMap.put(username, oid);
		
    }
 
    @OnMessage
    public void onMessage(Session session, String message, @PathParam("username") String username, @PathParam("oid") Integer oid) throws IOException 
    {
        // Handle new messages
    	Users recipient = null;
    	Users sender = userService.getUserByUsername(username);
    	logger.info("Entered into Message: Got Message:"+message);
    	if(userService.getUserByUsername(username).getUserType() == 2) {
    		recipient = orderService.getOrderByOid(oid).getCustomer();
    		
    	}
    	if(userService.getUserByUsername(username).getUserType() == 1) { 
    		recipient = orderService.getOrderByOid(oid).getChef();
    	}
    	
    	if(recipient.equals(null)) logger.info("Recipient is invalid");
    	else {
    		Session toSend = usernameSessionMap.get(recipient.getUsername());
    		toSend.getBasicRemote().sendText(sender.getName() + ": " + message);
    		session.getBasicRemote().sendText(sender.getName() + ": " + message);
    	}
    }
 
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) throws IOException
    {
    	logger.info(username + "Entered into Close");
    	
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) 
    {
        // Do error handling here
    	logger.info("Entered into Error");
    }
    

}
