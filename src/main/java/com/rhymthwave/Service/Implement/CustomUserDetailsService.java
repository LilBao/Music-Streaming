package com.rhymthwave.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AuthorDAO;
import com.rhymthwave.entity.Author;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AuthorDAO authorDAO;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Author user = authorDAO.findByEmailAccount(username);
		if(user != null) {
			return new CustomUserDetails(user);
		}

        throw new UsernameNotFoundException("User not found with Email: " + username);

	}
	

}
