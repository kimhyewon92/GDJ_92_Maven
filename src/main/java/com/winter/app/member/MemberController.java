package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("join")
	public void join() throws Exception {}
	
	@PostMapping("join")
	public String join(MemberVO memberVO, MultipartFile profile) throws Exception {
		int result = memberService.join(memberVO, profile);
		return "redirect:/";
	}
	
	@GetMapping("login")
	public void login() throws Exception {}
	
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession session) throws Exception{
		memberVO = memberService.login(memberVO);
		
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
		}
		
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		
		return "redirect:/";
	}
	
}
