package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.Pager;

public interface BoardService {
	
	// list
	public List<BoardVO> list(Pager pager) throws Exception;
	
	// detail
	public BoardVO detail(BoardVO boardVO) throws Exception;
	
	// insert
	public int insert(BoardVO boardVO, MultipartFile attaches) throws Exception;
	
	// update
	public int update(BoardVO boardVO) throws Exception;
	
	// delete
	public int delete(BoardVO boardVO) throws Exception;
	
}
