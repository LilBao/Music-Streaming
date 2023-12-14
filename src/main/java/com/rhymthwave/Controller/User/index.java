package com.rhymthwave.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class index {
	
	@GetMapping("home")
	public String layoutUser() {
		return "User/index";
	}
	
	@GetMapping("/login")
	public String loginLayout() {
		return "User/login";
	}
}
