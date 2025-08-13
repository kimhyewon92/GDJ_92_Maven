package com.winter.app.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
	
	@Bean
	FilterRegistrationBean<Filter> filterRegistrationBean(){
		FilterRegistrationBean<Filter> fr = new FilterRegistrationBean<>();
		
		fr.setFilter(new TestFilter());
		fr.addUrlPatterns("");
		fr.setOrder(2);
		
		return fr;
	}
	
	// 아래처럼 여러개 만들어서 하면됨..
	@Bean
	FilterRegistrationBean<Filter> adminCheckFilter(){
		FilterRegistrationBean<Filter> fr = new FilterRegistrationBean<>();
		
		fr.setFilter(new AdminCheckFilter());
		fr.addUrlPatterns("/notice/add", "/notice/update", "/notice/delete");
		fr.setOrder(1);
		
		return fr;
	}
	
}
