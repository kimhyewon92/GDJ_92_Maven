package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

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
	
	//@Test
	void listTest() throws Exception{
		List<BoardVO> list = noticeDAO.list();
		
		// 단정문
		assertNotEquals(0, list);
	}
	
	//@Test
	void detailTest() throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(7L);
		BoardVO boardVO = noticeDAO.detail(noticeVO);
		log.info("result : {}", boardVO);
//		log.info("result : {},{}", boardVO, boardVO);
		assertNotNull(boardVO);
	}
	
	@Test
	void insertTest() throws Exception {
		for(int i=0;i<105;i++) {
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setBoardTitle("title"+i);
			noticeVO.setBoardContents("contents"+i);
			noticeVO.setBoardWriter("writer"+i);
			int result = noticeDAO.insert(noticeVO);
			if(i%10 == 0) {
				Thread.sleep(500);
			}
		}
		// 단정문
		// assertEquals(1, result);
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
	void deleteTest() throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum((long)1);
		int result = noticeDAO.delete(noticeVO);
		
		// 단정문
		assertEquals(1, result);
	}
}
