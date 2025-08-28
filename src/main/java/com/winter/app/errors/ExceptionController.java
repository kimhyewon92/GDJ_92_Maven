package com.winter.app.errors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//모든 에러 처리하는 전역
@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(exception = NullPointerException.class)
	public String error(Model model) { //model에 에러메세지 작성하여 보내기
		return "errors/error";
	}
	
	@ExceptionHandler(exception = NumberFormatException.class)
	public String error2() {
		return "errors/error";
	}
	
	@ExceptionHandler(exception = Exception.class)
	public String error3() {
		return "errors/error";
	}
	
	@ExceptionHandler(exception = Throwable.class)
	public String error4() {
		return "errors/error";
	}
	
}
