package com.rhymthwave.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rhymthwave.Service.Implement.CustomUserDetailsService;
import com.rhymthwave.Utilities.JWT.JwtAuthentitationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthentitationFilter jwtAuthentitationFilter() {
		return new JwtAuthentitationFilter();
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationProvider AuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		
		return http.cors().and().csrf().disable()
				.authorizeHttpRequests((authz) -> authz
				//All
				.requestMatchers(HttpMethod.GET,"/api/v1/users").permitAll()
				.requestMatchers(HttpMethod.POST,"/api/v1/users/**").permitAll()
				.requestMatchers(HttpMethod.PUT).permitAll()
				.requestMatchers(HttpMethod.DELETE).permitAll()
				)
				.authenticationProvider(AuthenticationProvider())
				.addFilterBefore(jwtAuthentitationFilter(),UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
}