package com.rhymthwave.Controller.User;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class index implements ErrorController{
	
	@GetMapping("")
	public String layoutUser() {
		return "User/index";
	}
	
	@GetMapping("/signin")
	public String loginLayout() {
		return "User/login";
	}
	
	@GetMapping("/subscription")
	public String premium() {
		return "User/layoutSub";
	}
	
	@GetMapping("/error/404")
	public String err404() {
		return "User/404";
	}
	
	public String getErrorPath() {
        return "/error/404";
    }
}
