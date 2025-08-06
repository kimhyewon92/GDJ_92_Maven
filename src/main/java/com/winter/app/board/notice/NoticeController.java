package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Value("${board.notice}")
	private String name;
	
	@ModelAttribute("board")
	public String getBoard() {
		return name;
	}
	
	// @ModelAttribute Pager pager 이렇게.. jsp페이지에서는 타입앞글자 소문자로 자동저장됨..
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception{
		// model: spring 리퀘스트 라이프사이클 유사, 스프링 컨트롤러에서 jsp로 전달할때 사용, 매개변수에 선언
		List<BoardVO> list = noticeService.list(pager);
		model.addAttribute("list", list);
		
		return "board/list";
	}

//	public void detail(Long boardNum) throws Exception {
	
	@GetMapping("detail")
	public String detail(NoticeVO noticeVO, Model model) throws Exception {
		BoardVO boardVO = noticeService.detail(noticeVO);
		model.addAttribute("boardVO", boardVO);
		return "board/detail";
	}
	
	@GetMapping("add")
	public String insert() {
		return "board/add";
	}
	
	@PostMapping("add")
	public String insert(NoticeVO noticeVO) throws Exception{
		int result = noticeService.insert(noticeVO);
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String update(BoardVO noticeVO, Model model) throws Exception{
		BoardVO boardVO = noticeService.detail(noticeVO);
		model.addAttribute("boardVO", boardVO);
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(NoticeVO noticeVO, Model model) throws Exception{
		int result = noticeService.update(noticeVO);
		
		String msg = "수정 실패";
		
		if (result > 0) {
			msg="수정이 완료되었습니다.";
		}
		
		String url = "./detail?boardNum="+noticeVO.getBoardNum();
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result"; //"redirect:./detail?boardNum="+noticeVO.getBoardNum(); //redirect는 get 형식
	}
	
	@PostMapping("delete")
	public String delete(NoticeVO noticeVO, Model model) throws Exception{
		int result = noticeService.delete(noticeVO);
		
		String msg = "삭제 실패";
		
		if (result > 0) {
			msg = "삭제가 완료되었습니다.";
		}
		
		String url = "./list";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
}

