package com.module.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.module.beans.MyUser;
import com.module.beans.User;
import com.module.repositories.JpaUserAuthRepository;

//@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private JpaUserAuthRepository jpaRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = jpaRepo.findByUsername(username);
		
		user.orElseThrow(()->new UsernameNotFoundException("User Not Found: "+username));
		
		MyUser myUser = user.map(MyUser::new).get();
		
		return myUser;
	}
}
