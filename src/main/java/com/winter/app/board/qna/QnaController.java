package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String name;
	
	@ModelAttribute("board")
	public String getBoard() {
		return name;
	}
	
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardVO> list = qnaService.list(pager);
		model.addAttribute("pager", pager);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(QnaVO qnaVO, Model model) throws Exception {
		model.addAttribute("boardVO", qnaService.detail(qnaVO));
		return "board/detail";
	}
	
	@GetMapping("reply")
	public String reply(QnaVO qnaVO, Model model) throws Exception {
		model.addAttribute("boardVO", qnaVO);
		
		return "board/add";
	}
	
	@PostMapping("reply")
	public String replyUpdateInsert(QnaVO qnaVO, Model model) throws Exception {
		int result = qnaService.reply(qnaVO);
		
		String msg = "답글 등록 실패";
		
		if(result > 0) {
			msg = "답글 등록이 완료되었습니다.";
		}
		
		String url = "./list";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@GetMapping("add")
	public String insert() throws Exception {
		return "board/add";
	}
	
	@PostMapping("add")
	public String insert(QnaVO qnaVO, MultipartFile attaches, Model model) throws Exception {
		int result = qnaService.insert(qnaVO, attaches);
		
		String msg = "등록 실패";
		
		if (result > 0) {
			msg = "등록이 완료되었습니다.";
		}
		
		String url = "./list";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@GetMapping("update")
	public String updatePage(QnaVO qnaVO, Model model) throws Exception {
		model.addAttribute("boardVO", qnaService.detail(qnaVO));
		return "board/add";
	}

	@PostMapping("update")
	public String update(QnaVO qnaVO, Model model) throws Exception {
		int result = qnaService.update(qnaVO);
		
		String msg = "수정 실패";
		
		if (result > 0) {
			msg = "수정이 완료되었습니다.";
		}
		
		String url = "./detail?boardNum="+qnaVO.getBoardNum();
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String delete(QnaVO qnaVO, Model model) throws Exception {
		int result = qnaService.delete(qnaVO);
		
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
