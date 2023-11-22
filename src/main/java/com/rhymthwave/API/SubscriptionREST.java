package com.rhymthwave.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Subscription;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SubscriptionREST {
	
	private final CRUD<Subscription,Integer>  crudSubscription;
	
	@GetMapping("/api/v1/subscription/{id}")
	public ResponseEntity<MessageResponse> findOne(@PathVariable("id") Integer subId){
		return ResponseEntity.ok(new MessageResponse(true, "success",crudSubscription.findOne(subId)));
	}
	
	@GetMapping("/api/v1/subscription")
	public ResponseEntity<MessageResponse> findAll(){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudSubscription.findAll()));
	}
}