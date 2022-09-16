package com.module.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.module.beans.AuthenticationRequest;
import com.module.beans.AuthenticationResponse;
import com.module.services.UserAuthService;
import com.module.util.JWTUtil;

@RestController
public class HomeResource {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUtil jwtTokenUtil;
	@Autowired
	private UserAuthService authService;
	
	private String style1 = "'color:lightgreen;font-size:25px;'";
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = authService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@RequestMapping("guest")
	public String guest() {
		
		return "<div style="+style1+">Welcome to secure app- guest page</div>";
	}
	
	@RequestMapping("user")
	public String user() {
		
		return "<div style="+style1+">Welcome to secure app- user page</div>";
	}
	
	@RequestMapping("admin")
	public String admin() {
		
		return "<div style="+style1+">Welcome to secure app- admin page</div>";
	}
	
	@RequestMapping("home")
	public String home() {
		
		return "<div style="+style1+">Welcome to secure app- home page</div>";
	}
}
