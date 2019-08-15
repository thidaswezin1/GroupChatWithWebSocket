package com.isgm.chatting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isgm.chatting.entity.User;
import com.isgm.chatting.repository.UserRepository;
@Service
@Transactional
public class UserService implements UserDetailsService{
	UserRepository userRepo;
	PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepo,PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder=passwordEncoder;
		
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println("User name!!!!"+username);
		User user =  (User) userRepo.findByName(username);
		//System.out.println("existing user >>>>"+user.getName());
		if (user == null) {
            throw new BadCredentialsException("User don't exist.");
        }
		List<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("Manager"));
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorityList);
		
	}
}
