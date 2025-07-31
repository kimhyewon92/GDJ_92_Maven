package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;

	@Test
	void detailTest() throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(7L);
		BoardVO boardVO = noticeDAO.detail(noticeVO);
		log.info("result : {}", boardVO);
//		log.info("result : {},{}", boardVO, boardVO);
		assertNotNull(boardVO);
	}
	
	//@Test
	void insertTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title5");
		noticeVO.setBoardContents("contents5");
		noticeVO.setBoardWriter("writer5");
		int result = noticeDAO.insert(noticeVO);
		
		// 단정문
		assertEquals(1, result);
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
	
	//@Test
	public void delete() throws Exception{
		int result = noticeDAO.delete(3);
		
		// 단정문
		assertEquals(1, result);
	}
}
