package com.winter.app.account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDAO {

	public int add(List<AccountVO> list) throws Exception;

}
