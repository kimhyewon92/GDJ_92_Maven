package com.winter.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// 정적자원들을 Security에서 제외
	
//	@Bean
//	WebSecurityCustomizer customizer() {
//		
//	}
	
}
