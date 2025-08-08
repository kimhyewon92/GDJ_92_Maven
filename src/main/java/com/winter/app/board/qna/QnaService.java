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
	
	@Value("${board.qna}")
	private String board;
	
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = qnaDAO.totalCount(pager);
		pager.makeNum(totalCount);
		return qnaDAO.list(pager);
	}

	@Override
	public int insert(BoardVO boardVO, MultipartFile [] attaches) throws Exception {
		int result = qnaDAO.insert(boardVO);
		//ref값을 update
		result = qnaDAO.refUpdate(boardVO);
		
		if(attaches == null) {
			return result;
		}
		
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
		result = qnaDAO.insertFile(vo);
		
		}
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
	public int update(BoardVO boardVO, MultipartFile [] attaches) throws Exception {
		int result = qnaDAO.update(boardVO);
		
		if(attaches == null) {
			return result;
		}
		
		if (result > 0) {
			// 1. 파일을 HDD에 저장
			// transaction..?
			
				for (MultipartFile m : attaches) {
					if(m == null || m.isEmpty()) {
						continue;
					}
				String fileName = fileManager.fileSave(upload+board, m);
				
				// 2. 파일 정보를 FileDB에 저장
				BoardFileVO vo = new BoardFileVO();
				vo.setOriName(m.getOriginalFilename());
				vo.setSaveName(fileName);
				vo.setBoardNum(boardVO.getBoardNum());
				result = qnaDAO.insertFile(vo);
				}
		}
		return result;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		// 해당 글번호를 참조하는 다른글이 있는지 확인
		// 있으면 해당 글을 삭제하는것이 DB에 남겨두지만 상세페이지 이동이 안되게..
		// 해당 글의 첨부파일은 삭제
		// 삭제된 글입니다 표시..
		
		if() {
			
		}
		
		boardVO = qnaDAO.detail(boardVO);
		
		for(BoardFileVO vo : boardVO.getBoardFileVOs()) {
			fileManager.fileDelete(upload+board, vo.getSaveName());
		}
		int result = qnaDAO.fileDelete(boardVO);
		return qnaDAO.delete(boardVO);
	}	
	
	@Override
	public int fileDelete(BoardFileVO boardFileVO) throws Exception {
		// 1. File 조회
		boardFileVO = qnaDAO.fileDetail(boardFileVO);
		
		// 2. File 삭제
		boolean result = fileManager.fileDelete(upload+board, boardFileVO.getSaveName());
		
		// 3. DB 삭제
		return qnaDAO.fileDeleteOne(boardFileVO);
	}
}
