package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public List<BoardVO> list() throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.list();
	}
	
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return noticeDAO.detail(boardVO);
	}
	
	public int insert(BoardVO boardVO) throws Exception {
		return noticeDAO.insert(boardVO);
	}
	
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}
	
	public int delete(BoardVO boardVO) throws Exception {
		return noticeDAO.delete(boardVO);
	}
}
