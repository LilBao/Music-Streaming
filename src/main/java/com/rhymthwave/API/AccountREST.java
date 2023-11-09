package com.rhymthwave.API;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.Implement.AccountServiceImpl;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class AccountREST {
	private final CRUD<Account, String> crudAccount;

	private final AccountServiceImpl accountService;

	private final GetHostByRequest host;

	@GetMapping("/api/v1/account")
	public ResponseEntity<MessageResponse> findAccountByJWT(HttpServletRequest req) {
		String owner = host.getEmailByRequest(req);
		return ResponseEntity.ok(new MessageResponse(true, "success", crudAccount.findOne(owner)));
	}

	@GetMapping("/api/v1/account/{id}")
	public ResponseEntity<MessageResponse> findAccount(@PathVariable("id") String id) {
		return ResponseEntity.ok(new MessageResponse(true, "success", crudAccount.findOne(id)));
	}

	@Transactional
	@GetMapping("/api/v1/search/{keyword}")
	public ResponseEntity<MessageResponse> search(@PathVariable("keyword") String keyword) {
		return ResponseEntity.ok(new MessageResponse(true, "success", accountService.search(keyword)));
	}
}
