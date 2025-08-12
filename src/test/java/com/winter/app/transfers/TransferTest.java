package com.winter.app.transfers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.member.MemberVO;
import com.winter.app.transfer.Pay;
import com.winter.app.transfer.Transfers;

@SpringBootTest
class TransferTest {

	@Autowired
	private Transfers transfers;
	
	@Autowired
	private Pay pay;
	
	@Test
	void test() {
		
		transfers.takeBus("111");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("user");
		memberVO = transfers.takeSubway(memberVO);
	}

}
