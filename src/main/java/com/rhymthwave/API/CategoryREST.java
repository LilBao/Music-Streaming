package com.rhymthwave.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/caegories/")
@RequiredArgsConstructor
public class CategoryREST {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("{keyword}")
	public ResponseEntity<MessageResponse> getAll(@Param("keyword") String kw) {
		return ResponseEntity.ok(new MessageResponse(true, "success", categoryService.SearchMedia(kw)));
	}

}
