package com.rhymthwave.API_Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.ServiceAdmin.IArtistService;
import com.rhymthwave.entity.Artist;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/artist")
public class API_Artist {
	
	@Autowired
	private  IArtistService artistService;
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getOneArtistByEmail(@PathVariable("email") String email) {

		Artist artist = artistService.getOneArtistByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", artist));
	}

}
