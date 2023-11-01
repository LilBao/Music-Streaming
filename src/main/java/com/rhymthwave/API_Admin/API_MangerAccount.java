package com.rhymthwave.API_Admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.ServiceAdmin.IAccountServiceAdmin;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.TypeEnum.EROLE;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/account")
@RequiredArgsConstructor
public class API_MangerAccount {
		
	private final IAccountServiceAdmin accountServiceAdmin;
	
	@GetMapping()
	public ResponseEntity<?> getAllUser(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "sortBy", required = false, defaultValue = "asc") String sortBy,
			@RequestParam(value = "sortfield", required = false, defaultValue = "email") String sortField,
			@RequestParam(value = "role", required = false, defaultValue = "USER") EROLE role) {
		List<Account> pages = accountServiceAdmin.findAllAccountByRole(page, sortBy, sortField,role);

		
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", pages));
	}
	
	@GetMapping("/{idUser}")
	public ResponseEntity<?> getOneUser(@PathVariable("idUser") String idUser) {
		Account account = accountServiceAdmin.findById(idUser);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", account));
	}
	


}
