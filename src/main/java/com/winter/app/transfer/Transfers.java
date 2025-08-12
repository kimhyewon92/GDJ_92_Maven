package com.winter.app.transfer;

import org.springframework.stereotype.Component;

import com.winter.app.member.MemberVO;

@Component
public class Transfers {
	
	public void takeBus(String num) {
		
		System.out.println("버스 이용");
	}
	
	public MemberVO takeSubway(MemberVO memberVO) {
		System.out.println("지하철 이용");
//		memberVO = new MemberVO();
//		memberVO.setUsername("test");
		
		return memberVO;
	}
	
	public void getTaxi() {
		System.out.println("택시 이용");
	}
	
}
