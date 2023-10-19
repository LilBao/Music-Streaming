package com.rhymthwave.API_Admin;

import java.io.File;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.ServiceAdmin.IDisplaySlide;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/display-slide")
@RequiredArgsConstructor
public class API_DisplaySlide {
	
	private final IDisplaySlide displaySlide;

	@GetMapping()
	public ResponseEntity<?> getAllDisplaySlide(){
		List<String> list = displaySlide.getAllPositionSlidesShowing();
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", list));
	}
	
}
