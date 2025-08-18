package com.winter.app.board;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO {
	
	private Long boardNum;
	//null 허용 안하고 문자 최소 1개이상
	@NotBlank
	private String boardTitle;
	private String boardContents;
	private String boardWriter;
	private LocalDateTime boardDate;
	private Long boardHit;
	
	private List<BoardFileVO> boardFileVOs;
			
}
