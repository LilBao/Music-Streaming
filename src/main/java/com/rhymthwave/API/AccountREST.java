package com.rhymthwave.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.Service.Implement.AccountServiceImpl;
import com.rhymthwave.entity.Account;

import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/accounts")
public class AccountREST {

	@Autowired
	private AccountServiceImpl accountService;
	
	
	@GetMapping("/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable  String email) {
        Account acc = accountService.findOne(email);
        if (acc != null) {
            return ResponseEntity.ok(acc);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
