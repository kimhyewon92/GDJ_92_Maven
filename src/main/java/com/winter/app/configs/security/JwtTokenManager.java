package com.winter.app.configs.security;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberDAO;
import com.winter.app.member.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {

	// Token을 생성하거나, Token을 검증
	
	
	// 노출금지, 모든 서버가 같은 값
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	@Value("${jwt.tokenValidTime}")
	private Long tokenValidTime;
	
	@Value("${jwt.issuer}")
	private String issuer;
	
	private SecretKey key;
	
	@Autowired
	private MemberDAO memberDAO;
	
	//생성자에서 코드 작성 가능
	// 생성자 호출 전에.. 초기화
	@PostConstruct
	public void init() {
		String k = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
		key = Keys.hmacShaKeyFor(k.getBytes());
	}
	
	// Token 발급 (로그인이 성공했을때 유저네임,패스워드 일치시)
	public String createToken(Authentication authentication) {
		return Jwts
			.builder()
			.subject(authentication.getName()) // subject: 사용자의 ID(username)
			.claim("roles", authentication.getAuthorities().toString())
			.issuedAt(new Date()) //Token을 생성한 시간
			.expiration(new Date(System.currentTimeMillis()+tokenValidTime)) //현재시간에서 3분뒤
			.issuer(issuer)
			.signWith(key)
			.compact()

		;
	}
	
	// Token 검증
	public Authentication getAuthenticationByToken(String token) throws Exception{
		// 검증
		Claims claims = Jwts
			.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			;
		
		// 검증 통과
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(claims.getSubject());
		UserDetails userDetails = memberDAO.login(memberVO);
		//securitycontext에 넣어줘야함..
		
		//MemberVO(UserDetails)를 Authentication으로 변경
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

		return authentication;
		
		// 검증실패는 Exception 발생
		
			
	}
	
	
}
