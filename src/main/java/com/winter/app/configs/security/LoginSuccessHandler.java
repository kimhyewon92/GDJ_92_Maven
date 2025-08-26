package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
// 그외 나머지여서 component 서비스도아니고..
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	// 로그인이 성공했을 때 실행
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		// 쿠키로 아이디 기억하기 설정 및 해제
		String rememberId = request.getParameter("rememberId");
		
		if(rememberId != null && rememberId.equals("1")) {
			Cookie cookie = new Cookie("rememberId", authentication.getName());
			cookie.setMaxAge(60 * 60); //무한대는 -1 지울때는 0
			cookie.setPath("/"); //해당서버 루트밑으로 모든..
			response.addCookie(cookie);
		} else {
			Cookie[] cookies = request.getCookies();
			
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("rememberId".equals(c.getName())) {
						c.setMaxAge(0);
						c.setPath("/");
						response.addCookie(c);
						break;
					}
				}
			}
		}
		
		log.info("로그인 성공");
		log.info(rememberId);
		
		response.sendRedirect("/"); //forward도 됨
	}
}
