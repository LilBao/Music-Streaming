package com.rhymthwave.Service;

import org.springframework.security.core.Authentication;

public interface JWTService {
	String generateTokenLogin(Authentication authentication);
	Boolean validateJwtToken(String authToken);
	String getUserNameFromJwtToken(String token);
}
