package com.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JWTSecureApp {
	
	public static void main(String[] args) {
		SpringApplication.run(JWTSecureApp.class, args);
	}
	
	//JWT are meant for Authorization, Not for Authentication
}
