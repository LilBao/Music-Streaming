package com.rhymthwave.API_Admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.SubscriptionService;
import com.rhymthwave.entity.Subscription;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/subscription")
@RequiredArgsConstructor
public class API_Subscription {

	private final CRUD<Subscription, Integer> crud;
	
	private final SubscriptionService subscriptionService;
	
	@PostMapping
	public ResponseEntity<?> createSubscription(@RequestBody Subscription subscription) {

		crud.create(subscription);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, "Successfully", subscription));
	}
	

	@GetMapping()
	public ResponseEntity<?> getAllSubscription() {

		List<Subscription> subscription =  crud.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", subscription));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSubscription(@PathVariable("id") Integer id ) {
		boolean status =  crud.delete(id);
		if(status == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(false, "don't exist"));

		}
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully"));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneSubscription(@PathVariable("id") Integer id ) {
	
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully",crud.findOne(id)));
	}
	
	@PutMapping()
	public ResponseEntity<?> updateSubscription(@RequestBody Subscription subscription) {
	
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully",crud.update(subscription)));
	}
	
	
	
}
