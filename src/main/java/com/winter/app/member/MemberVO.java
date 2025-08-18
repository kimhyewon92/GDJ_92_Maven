package com.winter.app.member;

import java.time.LocalDate;
import java.util.List;

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
public class MemberVO {
	
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
}
