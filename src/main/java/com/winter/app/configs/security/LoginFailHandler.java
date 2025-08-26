package com.winter.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//로그인이 실패하면 Security에서 Exception을 발생
		log.info(exception.getMessage()); 
		
		//ID가 틀린 경우: 아래
		//InternalAuthenticationServiceException: UserDetailsService returned null, which is an interface contract violation

		//password가 틀린 경우: 아래
		//BadCredentialsException: 자격 증명에 실패하였습니다.
		
		//MemberVO에서 isEnabled false 리턴으로 지정했을 때
		//DisabledException: 유효하지 않은 사용자입니다.
		
		//MemberVO에서 isAccountNonExpired false 리턴으로 지정했을 때
		//AccountExpiredException: 사용자 계정의 유효 기간이 만료 되었습니다.
		
		//MemberVO에서 isAccountNonLocked false 리턴으로 지정했을 때
		//LockedException: 사용자 계정이 잠겨 있습니다.
		
		//MemberVO에서 isCredentialsNonExpired false 리턴으로 지정했을 때
		//CredentialsExpiredException: 자격 증명 유효 기간이 만료되었습니다.
		//비밀번호사용기간이 지남
		
		log.info("{}", exception); 
		
		//request는 forward, response는 redirect
		
//		if(exception.getClass().getName().equals("Bad~")) 기니까
		String message = "관리자에게 문의하세요.";
		if(exception instanceof BadCredentialsException) {
			message = "Password가 틀렸습니다.";
		}
		if(exception instanceof DisabledException) {
			message = "유효하지 않은 사용자입니다.";
		}
		if(exception instanceof AccountExpiredException) {
			message = "사용자 계정의 유효 기간이 만료 되었습니다.";
		}
		if(exception instanceof LockedException) {
			message = "사용자 계정이 잠겨 있습니다.";
		}
		if(exception instanceof CredentialsExpiredException) {
			message = "자격 증명 유효 기간이 만료되었습니다.";
		}
		if(exception instanceof InternalAuthenticationServiceException) {
			message = "ID가 틀렸습니다.";
		}
		// 그외나머지,기타등등
		if(exception instanceof AuthenticationCredentialsNotFoundException) {
			message = "관리자에게 문의하세요.";
		}
		
		//forward setattribute el 꺼내도됨
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("./login?failMessage="+message);
	}

}
