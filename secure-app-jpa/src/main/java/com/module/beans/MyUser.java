package com.module.beans;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean isActive;
	List<GrantedAuthority> authority;
	
	
	public MyUser() {		}
	
	public MyUser(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.isActive = user.isActive();
		this.authority = Arrays.asList(user.getRoles().split(",")).stream().map(u->"ROLE_"+u.toUpperCase().trim()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authority;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}
}
