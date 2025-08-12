package com.winter.app.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	public int join(MemberVO memberVO) throws Exception;
	
	public int profileInsert(ProfileVO profileVO) throws Exception;
	
	public int addRole(Map<String, Object> map) throws Exception;
}
