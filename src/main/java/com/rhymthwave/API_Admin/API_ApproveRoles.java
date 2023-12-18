package com.rhymthwave.API_Admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.ServiceAdmin.IArtistService;
import com.rhymthwave.entity.Artist;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/approve-roles")
@RequiredArgsConstructor
public class API_ApproveRoles {

	private final IArtistService artistService;
	
	@PostMapping("/{idUser}")
	public ResponseEntity<?> approveRoles(@PathVariable("idUser") Long idUser){
		Artist artist = artistService.approveRolesArtist(idUser);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully"));
	}
}
