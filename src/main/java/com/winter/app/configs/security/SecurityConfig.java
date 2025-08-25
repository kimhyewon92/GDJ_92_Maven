package com.winter.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// 정적자원들을 Security에서 제외
	
	@Bean
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
		
	}
	
	// 인증(Authentication)과 권한(Authorization)의 설정
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.cors(cors-> cors.disable())
			.csrf(csrf-> csrf.disable())
			
			// 권한에 관련된 설정 (권한잡을떄 순서확인)
			.authorizeHttpRequests(auth->{
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
					.requestMatchers("/products/add", "/products/update", "/products/delete").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER')")
					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
					.anyRequest().permitAll()
					;
			})
			// form 관련 설정
			.formLogin(form->{
				form
					.loginPage("/member/login")
//					.usernameParameter("username") //기본설정된 이름이라 주석해도 됨
//					.passwordParameter("password") //기본설정된 이름이라 주석해도 됨
					.defaultSuccessUrl("/") //로그인 성공시 리다이렉트로 어디로 보낼건지..
					.failureUrl("/member/login") //로그인 실패시 어디로 보낼지
					;
			})
			//logout 설정
			.logout((logout)->{
				logout
					.logoutUrl("/member/logout")
					//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")); deprecated 되있음
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/")
					;
			})
			;
		// 빌드하면 위 내용이 들어간 타입이 나온다..
		return httpSecurity.build();
	}
}
