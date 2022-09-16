package com.module.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**	First Level of Security Implementation
 * 	1. Provided auth manager builder with in-memory user
 * 	2. No password encoder for learning
 */

@SuppressWarnings("deprecation")
@Configuration
public class SecureAppConfig extends WebSecurityConfigurerAdapter {
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//Don't call parent class->instead provide self auth configuration
		//super.configure(auth);
		
		auth.inMemoryAuthentication().withUser("arham").password("arham").roles("user")
		.and().withUser("hapheej").password("hapheej").roles("admin");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
			.antMatchers("/admin").hasRole("admin") //Matches with /admin while /** matches all
			.antMatchers("/user").hasRole("user")
			.antMatchers("/home").hasAnyRole("admin", "user")
			.antMatchers("/").permitAll()
			.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoderDecoder() {
		
		//return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();	//Since this is learning purpose
	}

}
