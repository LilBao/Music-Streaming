package com.rhymthwave.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class index {
	
	@GetMapping("")
	public String layoutUser() {
		return "User/index";
	}
	
	@GetMapping("/logins")
	public String loginLayout() {
		return "User/login";
	}
	
	@GetMapping("/subscription")
	public String premium() {
		return "User/layoutSub";
	}
}
