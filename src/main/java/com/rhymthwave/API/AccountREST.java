package com.rhymthwave.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Account;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class AccountREST {
	private final CRUD<Account,String> crudAccount;
	
	private final GetHostByRequest host;
	
	@GetMapping("/api/v1/account")
	public ResponseEntity<MessageResponse> findAccountByJWT(HttpServletRequest req){
		String owner = host.getEmailByRequest(req);
		return ResponseEntity.ok(new MessageResponse(true, "success",crudAccount.findOne(owner)));
	}


}
