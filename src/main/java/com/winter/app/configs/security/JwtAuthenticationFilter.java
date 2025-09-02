package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter{

	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		// TODO Auto-generated constructor stub
		super(authenticationManager);
		
		this.jwtTokenManager = jwtTokenManager;
	}
	
	// dofilter는 매개변수로 servletrequest 받는데 http사용해야해서 internal로 사용
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// Toekn을 검증
		
		// 1. 토큰을 꺼내기
		Cookie [] cookies = request.getCookies();
		String token = "";
		for (Cookie c : cookies) {
			if(c.getName().equals("accessToken")) {
				token = c.getValue();
				break;
			}
		}
		System.out.println("Token : "+token);
		
		//2.토큰을 검증
		if(token != null && token.length() != 0) {
			try {
				Authentication authentication = jwtTokenManager.getAuthenticationByToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println("authentication.getName() : "+authentication.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				// SecurityException || MalformedException || SignitureException : 유효하지 않는 JWT 서명
				// ExpiredJwtException : 기간이 만료된 Token
				// UnSupportedJwtException : 지원되지 않는 Token
				// IllegalArgumentsException : 잘못된 Token
			}
			
		}
		
		chain.doFilter(request, response); // 로그인 안되어있으면 다음으로 넘어가기..?
		
	}
	
}
