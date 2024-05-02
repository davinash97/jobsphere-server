package com.portal.jobconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.portal.jobconnect.controller")
public class JobconnectApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobconnectApplication.class, args);
	}
}