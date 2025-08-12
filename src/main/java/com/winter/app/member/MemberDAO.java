package com.winter.app.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public int join(MemberVO memberVO) throws Exception;
	
	public int profileInsert(ProfileVO profileVO) throws Exception;
}
