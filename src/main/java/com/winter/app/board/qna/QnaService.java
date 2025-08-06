package com.winter.app.board.qna;

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
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.notice}")
	private String board;
	
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = qnaDAO.totalCount();
		pager.makeNum(totalCount);
		return qnaDAO.list(pager);
	}

	@Override
	public int insert(BoardVO boardVO, MultipartFile attaches) throws Exception {
		int result = qnaDAO.insert(boardVO);
		//ref값을 update
		result = qnaDAO.refUpdate(boardVO);
		
		// 1. File을 HDD에 저장
		String fileName = fileManager.fileSave(upload+board, attaches);
		
		// 2. 저장된 파일의 정보를 DB에 저장
		BoardFileVO vo = new BoardFileVO();
		vo.setOriName(attaches.getOriginalFilename());
		vo.setSaveName(fileName);
		vo.setBoardNum(boardVO.getBoardNum());
		result = qnaDAO.insertFile(vo);
		
		return result;
	}

	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return qnaDAO.detail(boardVO);
	}
	
	public int reply(QnaVO qnaVO) throws Exception {
		QnaVO parent = (QnaVO)qnaDAO.detail(qnaVO);
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardStep(parent.getBoardStep()+1);
		qnaVO.setBoardDepth(parent.getBoardDepth()+1);
		int result = qnaDAO.replyUpdate(parent);
		
		result = qnaDAO.insert(qnaVO);
		return result;
	}

	@Override
	public int update(BoardVO boardVO) throws Exception {
		return qnaDAO.update(boardVO);
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return qnaDAO.delete(boardVO);
	}	
	
}
