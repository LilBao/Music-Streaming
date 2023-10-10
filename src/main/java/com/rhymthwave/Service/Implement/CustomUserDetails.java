package com.rhymthwave.Service.Implement;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rhymthwave.entity.Author;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails{
	
	private String email;
	
	private String password;
	
	private boolean isVerify;
	
	private List< GrantedAuthority> authorities;
 
	
	
	public CustomUserDetails(Author user) {
		
		this.email = user.getAccounts().getEmail();
		this.password = user.getAccounts().getPassword();
		this.isVerify = user.getAccounts().isVerify();
		this.authorities = Arrays.stream(user.getRole().getRole().name().split(",")).map(
				SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isVerify;
	}

}
