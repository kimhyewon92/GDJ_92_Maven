package com.winter.app.board;

public interface BoardDAO {
	
	// insert
	public int insert(BoardVO boardVO) throws Exception;
	
	// update
	public int update(BoardVO boardVO) throws Exception;
	
	// delete
	public int delete(int num) throws Exception;
	
}
