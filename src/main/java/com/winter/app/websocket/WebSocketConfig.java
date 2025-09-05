package com.winter.app.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket //웹소켓을 활성화
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private AddWebSocketHandler addWebSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry
			.addHandler(addWebSocketHandler, "/chat")
			.setAllowedOrigins("*")
			;
	}
	
}
