package com.rhymthwave.Service.Implement;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rhymthwave.entity.Account;

public class UserPrinciple implements UserDetails{

	private static final long serialVersionUID = 1L;

    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> roles;
	
    
	public UserPrinciple() {
	}
	
	public UserPrinciple(String email, String password, Collection<? extends GrantedAuthority> roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	public static UserPrinciple build(Account account) {
        List<SimpleGrantedAuthority> authorities = account.getAuthors().stream().map(au -> new SimpleGrantedAuthority(au.getRole().getRoleName())).toList();;

        return new UserPrinciple(
        		account.getUsername(),
        		account.getPassword(),
                authorities
        );
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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
		return true;
	}

}
