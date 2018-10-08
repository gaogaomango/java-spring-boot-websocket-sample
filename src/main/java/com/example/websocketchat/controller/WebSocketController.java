package com.example.websocketchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.example.websocketchat.model.ChatMessage;

@Controller
public class WebSocketController {
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/publicChatRoom")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		System.out.println("WebSocketController : sendMessage");

		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/publicChatRoom")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor accessor) {
		System.out.println("WebSocketController : addUser");
		
		accessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

}
