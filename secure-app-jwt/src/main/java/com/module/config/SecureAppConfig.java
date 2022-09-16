package com.module.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.module.beans.User;
import com.module.filters.JwtRequestFilter;
import com.module.repositories.JpaUserAuthRepository;
import com.module.services.UserAuthService;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecureAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthService authServive;
	@Autowired
	private JpaUserAuthRepository jpaRepo;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Value("${initialize.db}")
	private boolean initialize;
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		if(initialize)
			initializeDB().forEach(user->jpaRepo.save(user));
		
		auth.userDetailsService(authServive);
			
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/authenticate").permitAll().
						anyRequest().authenticated().and().
						exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

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
