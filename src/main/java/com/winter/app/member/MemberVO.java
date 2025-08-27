package com.winter.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.winter.app.member.validation.AddGroup;
import com.winter.app.member.validation.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO implements UserDetails, OAuth2User {
	
	@NotBlank(message = "ID는 필수입니다.", groups = AddGroup.class)
	private String username;
	@NotBlank
	@Size(min = 6, max = 8, groups = AddGroup.class)
	private String password;
	
	private String passwordCheck;
	@NotBlank(groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	@Email(groups = {AddGroup.class, UpdateGroup.class})
	private String email;
//	@Pattern(regexp ="")
	private String phone;

	@NotNull
	// notempty, notblank 는 안됨 (문자열..)
	@Past(groups = {AddGroup.class, UpdateGroup.class})
	private LocalDate birth;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	private ProfileVO profileVO;
	
	private List<RoleVO> roleVOs;
	
	// ---------------------------- Social
	
	private Map<String, Object> attributes;
	
	private String accessToken;
	
	private String sns;
	
	//accountNonExpired : DisabledException: 유효하지 않은 사용자입니다.
	//enabled : AccountExpiredException: 사용자 계정의 유효 기간이 만료 되었습니다.
	//accountNonLocked : LockedException: 사용자 계정이 잠겨 있습니다.
	//credentialsNonExpired : CredentialsExpiredException: 자격 증명 유효 기간이 만료되었습니다.
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> list = new ArrayList<>();
		
		for (RoleVO roleVO : roleVOs) {
			list.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		
		return list;
	}

	
	
}
