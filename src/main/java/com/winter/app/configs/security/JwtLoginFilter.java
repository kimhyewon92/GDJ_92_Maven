package com.winter.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 로그인 요청시 실행하는 필터
// username, password를 꺼내서 UserDetailService의 loadUserByUsername 호출
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	private JwtTokenManager jwtTokenManager;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		
		this.setFilterProcessesUrl("/member/loginProcess"); // 해당 url이 왔을때 아래 메서드 실행..attemptAuthentication
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		System.out.println("Jwt Login Filter ===============");
//		System.out.println(username);
//		System.out.println(password);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		// UsernamePasswordAuthenticationToken에서 UserDtailService의 loadUserByUsername을 호출하고
		// 패스워드가 일치하는지 판별하고 해당 Authentication 객체를 SecurityContextHolder에 담아줌
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 사용자의 정보로 Token을 생성
		String accessToken = jwtTokenManager.makeAccessToken(authResult);
		String refreshToken = jwtTokenManager.makeRefreshToken(authResult);
		
		Cookie cookie = new Cookie("accessToken", accessToken);
		cookie.setPath("/");
		cookie.setMaxAge(180);
		cookie.setHttpOnly(true);
		
		
		response.addCookie(cookie);
		
		cookie = new Cookie("refreshToken", refreshToken);
		cookie.setPath("/");
		cookie.setMaxAge(600);
		cookie.setHttpOnly(true); // 자바스크립트에서 직접적으로 접근하지 못하게 막아주는 것
		
		response.addCookie(cookie);
		
		response.sendRedirect("/");
		
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("failed.getMessage() : " + failed.getMessage());
		
		String message = "관리자에게 문의하세요.";
		if(failed instanceof BadCredentialsException) {
			message = "Password가 틀렸습니다.";
		}
		if(failed instanceof DisabledException) {
			message = "유효하지 않은 사용자입니다.";
		}
		if(failed instanceof AccountExpiredException) {
			message = "사용자 계정의 유효 기간이 만료 되었습니다.";
		}
		if(failed instanceof LockedException) {
			message = "사용자 계정이 잠겨 있습니다.";
		}
		if(failed instanceof CredentialsExpiredException) {
			message = "자격 증명 유효 기간이 만료되었습니다.";
		}
		if(failed instanceof InternalAuthenticationServiceException) {
			message = "ID가 틀렸습니다.";
		}
		// 그외나머지,기타등등
		if(failed instanceof AuthenticationCredentialsNotFoundException) {
			message = "관리자에게 문의하세요.";
		}
		
//		response.sendRedirect("/member/login");
		
		//forward setattribute el 꺼내도됨
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("./login?failMessage="+message);
	}
	
	
}
