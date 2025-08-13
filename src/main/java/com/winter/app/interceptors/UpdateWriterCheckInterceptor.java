package com.winter.app.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateWriterCheckInterceptor implements HandlerInterceptor{
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		if(request.getMethod().toUpperCase().equals("POST")) {
			return;
		}
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		
		BoardVO boardVO = (BoardVO) modelAndView.getModel().get("boardVO");
		
		if(!memberVO.getUsername().equals(boardVO.getBoardWriter())) {
			modelAndView.setViewName("commons/result");
			modelAndView.addObject("msg", "작성자만 가능");
			modelAndView.addObject("url", "./list");
//			modelAndView.addObject("boardVO", null);
		}
		
		
	}
}
