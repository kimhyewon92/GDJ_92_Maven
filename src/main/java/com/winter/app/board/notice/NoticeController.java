package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public String list(Model model) throws Exception{
		// model: spring 리퀘스트 라이프사이클 유사, 스프링 컨트롤러에서 jsp로 전달할때 사용, 매개변수에 선언
		List<BoardVO> list = noticeService.list();
		
		model.addAttribute("list", list);
		
		return "notice/list";
	}

//	public void detail(Long boardNum) throws Exception {
	
	@GetMapping("detail")
	public String detail(NoticeVO noticeVO, Model model) throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		model.addAttribute("boardVO", boardVO);
		return "notice/detail";
	}
	
//	@GetMapping("add")
//	public void insert() throws Exception{
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContents("contents");
//		noticeVO.setBoardWriter("writer");
//		int result = noticeDAO.insert(noticeVO);
//	}
//	
//		
//	public void update() throws Exception{
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardNum(1L);
//		noticeVO.setBoardTitle("title4");
//		noticeVO.setBoardContents("contents4");
//		int result = noticeDAO.update(noticeVO);
//	}
//	
//	@GetMapping("delete")
//	public void delete() throws Exception{
//		int result = noticeDAO.delete(2);
//	}
	
}
