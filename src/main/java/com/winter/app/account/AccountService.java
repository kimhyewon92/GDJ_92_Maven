package com.winter.app.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.member.MemberDAO;
import com.winter.app.member.MemberVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	public int add(Long [] productNum, MemberVO memberVO) throws Exception{
		List<AccountVO> list = new ArrayList<>();
		
		for(Long num: productNum) {
			AccountVO accountVO = new AccountVO();
			accountVO.setUsername(memberVO.getUsername());
			accountVO.setProductNum(num);
			accountVO.setAccountNum(String.valueOf(System.currentTimeMillis()));
			list.add(accountVO);
			Thread.sleep(10);
		}
		
		int result = accountDAO.add(list);
		if(result > 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("username", memberVO.getUsername());
			map.put("list", Arrays.asList(productNum));
			result = memberDAO.cartDelete(map);
		}
		
		return result;
	}
	
	
}
