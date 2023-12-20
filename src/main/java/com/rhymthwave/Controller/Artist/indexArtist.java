package com.rhymthwave.Controller.Artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rhymthwave.Utilities.GetHostByRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class indexArtist {
	
	@Autowired
	GetHostByRequest byRequest;
	
	@GetMapping("/artist")
	public String layoutArtist(HttpServletRequest request) {
		System.out.println(byRequest.getEmailByRequest(request));
		return "Artist/ArtistControl";
	}
	
	@GetMapping("claim")
	public String claimArtist() {
		return "Artist/Information";
	}
	
	@GetMapping("artist/home")
	public String layoutArtistHome() {
		return "Artist/Artist";
	}
	
}
