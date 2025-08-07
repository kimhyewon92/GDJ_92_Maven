package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.notice}")
	private String board;
	
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = noticeDAO.totalCount(pager);
		pager.makeNum(totalCount);
		return noticeDAO.list(pager);
	}
	
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return noticeDAO.detail(boardVO);
	}
	
	public int insert(BoardVO boardVO, MultipartFile [] attaches) throws Exception {
		int result = noticeDAO.insert(boardVO);
		
		for(MultipartFile m:attaches) {
			if(m == null || m.isEmpty()) {
				continue;
			}
			
			// 1. File을 HDD에 저장
			String fileName = fileManager.fileSave(upload+board, m);
			
			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO vo = new BoardFileVO();
			vo.setOriName(m.getOriginalFilename());
			vo.setSaveName(fileName);
			vo.setBoardNum(boardVO.getBoardNum());
			result = noticeDAO.insertFile(vo);
		}
		return result;
	}
	
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}
	
	public int delete(BoardVO boardVO) throws Exception {
		boardVO = noticeDAO.detail(boardVO);
		
		for(BoardFileVO vo : boardVO.getBoardFileVOs()) {
			fileManager.fileDelete(upload+board, vo.getSaveName());
		}
		// 제약조건 Cascade로 테이블 다시 만들거나..
		int result = noticeDAO.fileDelete(boardVO);
		return noticeDAO.delete(boardVO);
	}
}
