package com.winter.app.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@GetMapping("add")
	public void insert() throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title");
		noticeVO.setBoardContents("contents");
		noticeVO.setBoardWriter("writer");
		int result = noticeDAO.insert(noticeVO);
	}
	
	@GetMapping("update")
	public void update() throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum((long)1);
		noticeVO.setBoardTitle("title4");
		noticeVO.setBoardContents("contents4");
		int result = noticeDAO.update(noticeVO);
	}
	
	@GetMapping("delete")
	public void delete() throws Exception{
		int result = noticeDAO.delete(2);
	}
}
