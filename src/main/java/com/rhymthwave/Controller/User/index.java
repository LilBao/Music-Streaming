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
	
	@GetMapping("/account")
	public String account() {
		return "User/account";
	}
	
	@GetMapping("/news/home")
	public String newsAll() {
		return "Artist/NewsAll";
	}
	
	@GetMapping("/news/home/{id}")
	public String news() {
		return "Artist/News";
	}
	
	@GetMapping("/signup")
	public String sigup() {
		return "User/signup";
	}
	
	@GetMapping("/forgot")
	public String fogot() {
		return "User/fogotpassword";
	}
	
	@GetMapping("/buys-ads")
	public String buyAds() {
		return "User/advertising";
	}

	@GetMapping("/error/404")
	public String err404() {
		return "User/404";
	}
	
	public String getErrorPath() {
        return "/error/404";
    }

}
