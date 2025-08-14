package com.winter.app.account;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AccountDAOTest {

	@Autowired
	private AccountDAO accountDAO;
	
	@Test
	@DisplayName("Account Add Test")
	void testAdd() throws Exception {
		List<AccountVO> list = new ArrayList<>();
		AccountVO accountVO = new AccountVO();
		accountVO.setAccountNum("1");
		accountVO.setUsername("user01");
		accountVO.setProductNum(1L);
		list.add(accountVO);
		
		accountVO = new AccountVO();
		accountVO.setAccountNum("2");
		accountVO.setUsername("GGGG");
		accountVO.setProductNum(3L);
		list.add(accountVO);
		
		int result = accountDAO.add(list);
		
		assertNotEquals(0, result);
	}

}
