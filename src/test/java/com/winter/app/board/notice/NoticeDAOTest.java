package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	//@Test
	void insertTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title3");
		noticeVO.setBoardContents("contents3");
		noticeVO.setBoardWriter("writer3");
		int result = noticeDAO.insert(noticeVO);
		
		// 단정문
		assertEquals(0, result);
	}
	
	//@Test
	void updateTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum((long)1);
		noticeVO.setBoardTitle("title4");
		noticeVO.setBoardContents("contents4");
		int result = noticeDAO.update(noticeVO);
		
		// 단정문
		assertEquals(1, result);
	}
	
	@Test
	public void delete() throws Exception{
		int result = noticeDAO.delete(2);
		
		// 단정문
		assertEquals(1, result);
	}

}
