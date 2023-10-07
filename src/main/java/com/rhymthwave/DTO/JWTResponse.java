//package com.rhymthwave.DTO;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//
//public class JWTResponse {
//	private String email;
//	private String token;
//	private String type = "Bearer";
//	private Collection<? extends GrantedAuthority> roles;
//
//	public JWTResponse() {
//
//	}
//
//	public JWTResponse(String email, String token, String type, Collection<? extends GrantedAuthority> roles) {
//		this.email = email;
//		this.token = token;
//		this.type = type;
//		this.roles = roles;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public Collection<? extends GrantedAuthority> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Collection<? extends GrantedAuthority> roles) {
//		this.roles = roles;
//	}
//
//}
