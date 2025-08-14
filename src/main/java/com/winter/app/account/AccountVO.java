package com.winter.app.account;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountVO {
	
	private String accountNum;
	private String username;
	private Long productNum;
	private LocalDate accountDate;
	private Long accountBalance;
	
	
}
