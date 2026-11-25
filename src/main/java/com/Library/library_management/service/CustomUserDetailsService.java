package com.Library.library_management.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Library.library_management.entity.AppUser;
import com.Library.library_management.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	 private final UserRepository userRepository;

	    public CustomUserDetailsService(
	            UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
	 @Override
	 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		 AppUser appUser = userRepository.findByUsername(username).orElseThrow(() ->
         new UsernameNotFoundException(
                 "User not found: " + username
             )
         );
		 
		 return User.builder()
	                .username(appUser.getUsername())
	                .password(appUser.getPassword())
	                .roles(appUser.getRole())
	                .build();
	 }

}
