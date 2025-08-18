package com.winter.app.schedule;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberDAO;

@Component
public class TestScheduler {
	
	@Autowired
	private MemberDAO memberDAO;
	
//	@Scheduled(fixedRate=2000)
	//@Scheduled(fixedRateString="2000", initialDelay = 1000)
	public void m1() throws Exception {
		System.out.println(LocalDateTime.now());
		System.out.println("========================m1");
		Thread.sleep(3000);
		System.out.println(LocalDateTime.now());
	}
	
	//@Scheduled(fixedDelay = 1000*60*60*24, initialDelayString = "1000")
	public void m2() throws Exception {
		System.out.println(LocalDateTime.now());
		System.out.println("========================m2");
		Thread.sleep(3000);
		System.out.println(LocalDateTime.now());
	}
	
	@Scheduled(cron = "3 * * * * *")
	public void m3() throws Exception {
		System.out.println(LocalDateTime.now());
		System.out.println("========================m3");
	}
}
