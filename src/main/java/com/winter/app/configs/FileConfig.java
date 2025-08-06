package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
	
	@Value("${app.upload}")
	private String path; // D:/upload/
	
	@Value("${app.url}")
	private String url; // /files/ (notice/file명인데..)
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry
			.addResourceHandler(url)
			.addResourceLocations("file:\\"+path); //역슬래시 2개는 이스케이프때문.. 컨트롤러 안보내고 path로 해달라고요청
	}
}
