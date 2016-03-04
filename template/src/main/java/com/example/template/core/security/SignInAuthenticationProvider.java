package com.example.template.core.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.template.components.repository.UserRepository;
import com.example.template.dto.user.User;
import com.example.template.exceptions.user.NotFoundUserException;

public class SignInAuthenticationProvider implements AuthenticationProvider{
	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
		
		User user = findUserInfo(authToken.getName());
		if(user == null) {
			throw new NotFoundUserException(authToken.getName());
		}
		
		if(!matchPassword(user.getPassword(),authToken.getCredentials())) {
			throw new BadCredentialsException("Not matching ID or Password");
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, getGrantedAuthorities(user));
	}

	
	private boolean matchPassword(String password, Object credentials) {
		return passwordEncoder.matches((String)credentials, password);
	}

	private User findUserInfo(String id) {
		return userRepository.getUserById(id);
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		if(user.getRole_name() == null) {
			return Collections.emptyList();
		}
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole_name()));
		
		return grantedAuthorities;
	}
	
}
