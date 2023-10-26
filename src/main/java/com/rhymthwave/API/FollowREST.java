package com.rhymthwave.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.AuthorService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.FollowService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.Follow;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class FollowREST {
	private final FollowService followSer;
	
	private final AuthorService authorSer;
	
	private final CRUD<Follow, Long> crudFollow;
	
	private final GetHostByRequest host;
	
	@PostMapping("/api/v1/follow")
	public ResponseEntity<MessageResponse> following(HttpServletRequest req, @RequestParam("email") String target, @RequestParam("type") Integer type){
		String main = host.getEmailByRequest(req);
		Author accountA = authorSer.findAuthor(1, main);
		Author accountB = authorSer.findAuthor(type, target);
		Follow followRaw = new Follow();
		Follow followData = followSer.snapFollow(followRaw, accountA, accountB);
		return ResponseEntity.ok(new MessageResponse(true,"success",crudFollow.create(followData)));
	}
	
	@DeleteMapping("/api/v1/follow")
	public ResponseEntity<MessageResponse> cancelFollow(HttpServletRequest req, @RequestParam("email") String target, @RequestParam("type") Integer type){
		String main = host.getEmailByRequest(req);
		Author accountA = authorSer.findAuthor(1, main);
		Author accountB = authorSer.findAuthor(type, target);
		Follow follow = followSer.findFollowByAccount(accountA, accountB);
		return ResponseEntity.ok(new MessageResponse(true,"success",crudFollow.delete(follow.getFollowerId())));
	}
}
