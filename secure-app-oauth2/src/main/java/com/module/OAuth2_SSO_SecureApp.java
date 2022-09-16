package com.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
public class OAuth2_SSO_SecureApp {
	
	public static void main(String[] args) {
		SpringApplication.run(OAuth2_SSO_SecureApp.class, args);
	}
	
	/**
		public class SecurityConf extends WebSecurityConfigurerAdapter {
			@Override
			protected void configure(HttpSecurity http) throws Exception {
    			http.oauth2Client(); //equivalent to @EnableOAuth2Client
    			http.oauth2Login();  //equivalent to @EnableOAuth2Sso

			}
		}
	 */
}
