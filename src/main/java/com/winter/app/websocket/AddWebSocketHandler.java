package com.winter.app.websocket;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddWebSocketHandler implements WebSocketHandler {

	private List<WebSocketSession> users = new ArrayList<>();
	
	private Map<String, WebSocketSession> map = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		// websocket으로 연결 되었을 떄 실행
//		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
//		authentication.getName();
//		map.put(authentication.getName(), session);
		
		Principal principal = session.getPrincipal();
		map.put(principal.getName(), session);
		
		log.info("{}", session);
		users.add(session);
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		// 사용자가 메세지를 전송했을 때 실행
		log.info("{}", message.getPayload());
		users.forEach(u->{
			try {
				u.sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
