package com.winter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class Gdj92MavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj92MavenApplication.class, args);
	}

}
