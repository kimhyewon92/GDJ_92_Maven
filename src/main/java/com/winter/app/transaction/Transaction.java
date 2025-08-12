package com.winter.app.transaction;

import org.springframework.stereotype.Component;

@Component
public class Transaction {
	
	public void t() {
		System.out.println("session을 false");
	}
	
	public void t2() {
		System.out.println("commit");
	}
	
}
