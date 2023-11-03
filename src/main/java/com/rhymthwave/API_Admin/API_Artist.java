package com.rhymthwave.API_Admin;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.ServiceAdmin.IArtistService;
import com.rhymthwave.entity.Artist;
import com.rhymthwave.entity.Country;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/artist")
@RequiredArgsConstructor
public class API_Artist {
	
	private final IArtistService artistService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneArtist(@PathVariable("id") Integer idArtist) {

		Artist artist = artistService.getOneArtist(idArtist);
		
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", artist));
	}

}
