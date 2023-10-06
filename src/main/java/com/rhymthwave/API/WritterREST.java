package com.rhymthwave.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.WritterService;
import com.rhymthwave.entity.Writter;

@RestController
@CrossOrigin("*")
public class WritterREST {
	
	@Autowired
	WritterService writterSer;
	
	@Autowired
	CRUD<Writter, Integer> crudWritter;
	
	@GetMapping("/api/v1/writter")
	public ResponseEntity<MessageResponse> getAllWritter(){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudWritter.findAll()));
	}
	
	@GetMapping("/api/v1/writter/{id}")
	public ResponseEntity<MessageResponse> getWritter(@PathVariable("id") Integer id){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudWritter.findOne(id)));
	}
	
	@PostMapping("/api/v1/writter")
	public ResponseEntity<MessageResponse> createWritter(@RequestBody Writter writter){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudWritter.create(writter)));
	}
}
