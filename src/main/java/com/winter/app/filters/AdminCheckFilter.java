package com.winter.app.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.winter.app.member.MemberService;
import com.winter.app.member.MemberVO;
import com.winter.app.member.RoleVO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AdminCheckFilter implements Filter {

	@Autowired
	private MemberService memberService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// admin 권한의 유무
		HttpServletRequest req = (HttpServletRequest) request;
		MemberVO memberVO = (MemberVO) req.getSession().getAttribute("member");
		boolean flag = false;
		
		for(RoleVO roleVO:memberVO.getRoleVOs()) {
			if(roleVO.getRoleName().equals("ROLE_ADMIN")) {
				flag = !flag;
				break;
			}
		}
	
		if(flag) {
			chain.doFilter(request, response);
		} else {
			req.setAttribute("msg", "권한이 없습니다.");
			req.setAttribute("url", "/");
			req.getRequestDispatcher("/WEB-INF/views/commons/result.jsp").forward(request, response);
		}
	}
	
}
