package com.winter.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

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
	
	//인증(Authentication)과 권한(Authorization)의 설정
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		//인증
		httpSecurity
			//CORS(Cross-Origin Resource Sharing) 기능 비활성화
			//람다 방식으로 CORS 설정을 커스터마이징할 수 있게 해주는 오버로드 메서드
			//cors() 이렇게만 쓰면 기본 CORS 설정을 활성화 (커스터마이징 X)
			//브라우저에서 다른 도메인(예: 프론트앱:3000 → 백엔드:8080) 요청이 거부될 수 있음
			.cors(cors-> cors.disable()) // "CORS 켜줄게, 근데 내가 직접 설정할게"
			//CSRF(Cross Site Request Forgery) 보호 비활성화
			//POST/PUT 요청 시 CSRF 토큰 없이도 요청 가능 (보안적으로 위험할 수 있음)
			.csrf(csrf-> csrf.disable())
			
			//권한
			//특정 URL접근에 대한 권한 설정
			.authorizeHttpRequests(auth->{
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
					.requestMatchers("/products/add", "/products/update", "/products/delete").hasAnyRole("MANAGER", "ADMIN")
					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
					.anyRequest().permitAll() //맨 위에 두면 아래 조건은 전혀 적용되지 않아요.
					;
			})
			;
			
		return httpSecurity.build();
	}
	
}
