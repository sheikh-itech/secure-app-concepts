package com.module.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
@EnableWebSecurity
public class SecureAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource data;
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*	//First time use [Initialize h2 db]
		auth.jdbcAuthentication()
			.dataSource(data)
			.withDefaultSchema()
			.withUser(User.withUsername("hapheej").password("hapheej").roles("admin", "user"))
			.withUser(User.withUsername("arham").password("arham").roles("user"));
			*/
		
		/**	This Data-Source will work for any type of data [oracle, MySql, Mongo] */
		
		auth.jdbcAuthentication()
		.dataSource(data)
		.usersByUsernameQuery("select username, password, enabled from users "
				+ "where username=?")
		.authoritiesByUsernameQuery("select username, authority from authorities "
				+ "where username=?");
			
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
		return NoOpPasswordEncoder.getInstance();
	}
}
