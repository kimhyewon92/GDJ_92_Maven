package com.winter.app.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.member.MemberService;

@Configuration
//@EnableWebSecurity(debug = true)
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
	
	@Autowired
	private JwtTokenManager jwtTokenManager; //token 생성하고 검증하는 역할..
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
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
				form.disable()
				//로그인하면 에러발생
				;
			})
//			.httpBasic((httpBasic)->{
//				httpBasic.disable();
//			})
			
			//logout 설정
			// 개발자가 아닌, Security Filter에서 처리
//			.logout((logout)->{
//				logout
//					.logoutUrl("/member/logout")
//					.addLogoutHandler(addLogoutHandler)
//					.logoutSuccessHandler(addLogoutSuccessHandler)
////					.logoutSuccessHandler(null)
//					//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")); deprecated 되있음
//					.invalidateHttpSession(true) //세션 유지시간을 0으로 맞춰서 지우기
//					.deleteCookies("JSESSIONID") //추가적으로 쿠키 지우고싶을때
//					.logoutSuccessUrl("/") //로그아웃 성공시 리다이렉트로 어디로 보낼건지..
//					;
//			})
			
			.logout(logout->{
				logout
				.logoutUrl("/member/logout")
				.invalidateHttpSession(true)
				.deleteCookies("accessToken", "refreshToken")
				.logoutSuccessUrl("/")
				;
			})
			
//			.rememberMe((remember)->{
//				remember
//					.rememberMeParameter("remember-me") //기본설정된 이름이라 주석해도 됨
//					.tokenValiditySeconds(30)
//					.key("rememberkey")
//					.userDetailsService(memberService)
//					.authenticationSuccessHandler(loginSuccessHandler)
//					.useSecureCookie(false)
//					;
//			})
			
			// Session 인증방식이 아닌
			// Token 인증방식이기 때문에 Session을 사용하지 않음
			
			.sessionManagement((s)->{
				s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				;
			})
			
			//
			.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			.addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			
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
