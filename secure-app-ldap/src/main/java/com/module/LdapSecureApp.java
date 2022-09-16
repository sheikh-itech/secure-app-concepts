package com.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LdapSecureApp {

	public static void main(String[] args) {
		SpringApplication.run(LdapSecureApp.class, args);
	}
	
	//Username-> ben
	//password-> benspassword
	//From reference-> https://spring.io/guides/gs/authenticating-ldap/
}
