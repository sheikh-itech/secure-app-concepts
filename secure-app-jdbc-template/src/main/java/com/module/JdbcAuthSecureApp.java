package com.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcAuthSecureApp {

	public static void main(String[] args) {
		SpringApplication.run(JdbcAuthSecureApp.class, args);
	}

	/**
	 * Un-Comment code from SecureAuthConfig.java file
	 * for initializing database first time
	 */
}
