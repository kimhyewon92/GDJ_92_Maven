package com.winter.app.home;

import java.security.Principal;
import java.util.Enumeration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpSession session) {
//		Enumeration<String> keys = session.getAttributeNames();
//		
//		while(keys.hasMoreElements()) {
//			log.info("key : {}", keys.nextElement());
//		}
//		
//		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
//		log.info("{}", obj.getClass().getName());
//		
//		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
//		
//		Authentication authentication = contextImpl.getAuthentication();
//		
//		log.info("Auth : {}", authentication);
//		
//		// ------------------------------------------
//		authentication = SecurityContextHolder.getContext().getAuthentication();
//		
//		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
		return "index";
	}
	
	@GetMapping("/info")
	public void info(Principal principal) {
		Authentication userDetails = (Authentication) principal;
		log.info("{}", principal);
		log.info("{}", principal.getName());
//		log.info(userDetails.getUsername());
//		log.info(userDetails.getPassword());
//		log.info("{}", userDetails.getAuthorities());
		
	}
	
}
