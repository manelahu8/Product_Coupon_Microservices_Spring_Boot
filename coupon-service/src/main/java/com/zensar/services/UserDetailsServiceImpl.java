package com.zensar.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zensar.entity.UserEntity;
import com.zensar.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity> userEntities=userRepository.findByUsername(username);
		
		UserEntity userEntity=userEntities.get(0);
		
		if(userEntities==null || userEntities.size()==0) {
			throw new UsernameNotFoundException(username);
		}
		
		List<GrantedAuthority> authorites=new ArrayList<GrantedAuthority>();
		authorites.add(new SimpleGrantedAuthority(userEntity.getRoles()));
		
		return new User(userEntity.getUsername(),userEntity.getPassword(),authorites);
	}

}
