package com.module.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecureAppConfig extends WebSecurityConfigurerAdapter {


	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
	      .ldapAuthentication()
	        .userDnPatterns("uid={0},ou=people")	//from ldap-data.ldif
	        .groupSearchBase("ou=groups")
	        .contextSource()
	          .url("ldap://localhost:8389/dc=springframework,dc=org") //from properties file
	          .and()
	        .passwordCompare()
	          .passwordEncoder(new BCryptPasswordEncoder())
	          .passwordAttribute("userPassword");	//from ldap-data.ldif
			
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		/*
		http.authorizeHttpRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/home").hasAnyRole("ADMIN", "USER")
			.antMatchers("/").permitAll()
			.and().formLogin();
		*/
		
		http.authorizeRequests()
			.anyRequest().fullyAuthenticated()
			.and().formLogin();
	}
}
