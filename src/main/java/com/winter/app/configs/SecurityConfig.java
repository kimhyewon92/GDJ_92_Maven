package com.winter.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//기본 index 페이지가 안 나와서 정적자원(css,js,image) url 경로들을 ignoring 해줌)
	//필터를 적용하지 않을 URL(예: 정적 리소스)을 설정
	@Bean
	/*public을 사용 했으나 default로 바꿔야 한다는 메세지 뜸*/
	WebSecurityCustomizer customizer() {
		
		// web => WebSecurity 타입
		return web -> {
			web
				.ignoring()
					.requestMatchers("/css/**")
					.requestMatchers("/js/**", "/vendor/**")
					.requestMatchers("/files/**")
					;
		};
		//여기까지 설정하면 spring security 로그인페이지는 뜨지 않고 localhost 페이지 나옴
				
				
	}
	
}
