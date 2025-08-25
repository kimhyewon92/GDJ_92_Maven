package com.winter.app.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberDAOTest {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void testPasswordUpdate() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("GGGG");
		memberVO.setPassword(passwordEncoder.encode("GGGG"));
		int result = memberDAO.passwordUpdate(memberVO);
	}

}
