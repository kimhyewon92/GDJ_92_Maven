package com.winter.app.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.interceptors.UpdateWriterCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry
		.addInterceptor(new UpdateWriterCheckInterceptor())
		.addPathPatterns("/notice/update", "/qna/update")
		;
		
		//또 등록시 registry부터..
	}
	
}
