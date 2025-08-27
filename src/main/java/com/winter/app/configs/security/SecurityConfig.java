package com.winter.app.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.winter.app.member.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailHandler loginFailHandler;
	
	@Autowired
	private AddLogoutHandler addLogoutHandler;
	
	@Autowired
	private AddLogoutSuccessHandler addLogoutSuccessHandler;
	
	@Autowired
	private MemberService memberService;
	
	@Bean
	WebSecurityCustomizer customizer() {
		
		// web => WebSecurity 타입
		// 정적자원들을 Security에서 제외
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
			
			// 권한에 관련된 설정 (권한잡을때 순서확인)
			.authorizeHttpRequests(auth->{
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN") //ROLE_ADMIN
					.requestMatchers("/products/add", "/products/update", "/products/delete").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER')")
					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
					.anyRequest().permitAll()
					;
			})
			// form 관련 설정
			// 개발자가 로그인 검증을 하지 않는다, Security Filter에서 검증
			.formLogin(form->{
				form
					.loginPage("/member/login")
//					.usernameParameter("username") //기본설정된 이름이라 주석해도 됨
//					.passwordParameter("password") //기본설정된 이름이라 주석해도 됨
//					.defaultSuccessUrl("/") //로그인 성공시 리다이렉트로 어디로 보낼건지..successHandler 쓸땐 안쓰기
					// .successForwardUrl("/") // forward 보냄.. 참고로
//					.successHandler(new LoginSuccessHandler()) //defaultSuccessUrl쓸땐 안쓰기
					.successHandler(loginSuccessHandler) //또는 위에 autowired, defaultSuccessUrl쓸땐 안쓰기
//					.failureUrl("/member/login") //로그인 실패시 리다이렉트로 어디로 보낼지, failureHandler쓸땐 안쓰기
//					.failureForwardUrl("/") //forward 보냄.. 참고로
					.failureHandler(loginFailHandler); //failureUrl쓸땐 안쓰기
			})
			//logout 설정
			// 개발자가 아닌, Security Filter에서 처리
			.logout((logout)->{
				logout
					.logoutUrl("/member/logout")
					.addLogoutHandler(addLogoutHandler)
					.logoutSuccessHandler(addLogoutSuccessHandler)
//					.logoutSuccessHandler(null)
					//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")); deprecated 되있음
					.invalidateHttpSession(true) //세션 유지시간을 0으로 맞춰서 지우기
					.deleteCookies("JSESSIONID") //추가적으로 쿠키 지우고싶을때
					.logoutSuccessUrl("/") //로그아웃 성공시 리다이렉트로 어디로 보낼건지..
					;
			})
			
			.rememberMe((remember)->{
				remember
					.rememberMeParameter("remember-me") //기본설정된 이름이라 주석해도 됨
					.tokenValiditySeconds(30)
					.key("rememberkey")
					.userDetailsService(memberService)
					.authenticationSuccessHandler(loginSuccessHandler)
					.useSecureCookie(false)
					;
			})
			
			.sessionManagement((s)->{
				s
				.invalidSessionUrl("/member/login")
				.maximumSessions(1) //-1은 무한
				.maxSessionsPreventsLogin(true) //false: 이전 사용자를 로그아웃, true: 현재 접속하려는 사용자 막기
				.expiredUrl("/member/login") //redirect
				;
			})
			
			.oauth2Login((o)->{
				o.userInfoEndpoint((user)->{
					user.userService(memberService);
				});
			})
			
			;
		// 빌드하면 위 내용이 들어간 타입이 나온다..
		return httpSecurity.build();
	}
}
