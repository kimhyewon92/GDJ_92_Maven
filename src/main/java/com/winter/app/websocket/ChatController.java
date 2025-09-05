package com.winter.app.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/*")
public class ChatController {
	
	@GetMapping("chat")
	public String chat() {
		return "chat/chat";
	}
	
	
}
