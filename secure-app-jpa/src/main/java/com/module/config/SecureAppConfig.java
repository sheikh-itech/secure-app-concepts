package com.module.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.module.beans.User;
import com.module.repositories.JpaUserAuthRepository;
import com.module.services.UserAuthService;

/**	First Level of Security Implementation
 * 	1. Provided auth manager builder with jpa user
 * 	2. No password encoder for learning
 */

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecureAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthService authServive;
	@Autowired
	private JpaUserAuthRepository jpaRepo;
	@Value("${initialize.db}")
	private boolean initialize;
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		if(initialize)
			initializeDB().forEach(user->jpaRepo.save(user));
		
		auth.userDetailsService(authServive);
			
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/home").hasAnyRole("ADMIN", "USER")
			.antMatchers("/").permitAll()
			.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoderDecoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}
	
	private List<User> initializeDB() {
		
		List<User> users = new ArrayList<>();
		
		User u1 = new User("hapheej", "hapheej", true, "admin, user");
		User u2 = new User("arham", "arham", true, "user");
		
		users.add(u1); 	users.add(u2);
		
		return users;
	}
}
