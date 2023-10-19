package com.rhymthwave.API_Admin;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.AccountService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.IMoodService;
import com.rhymthwave.Utilities.ExcelExportService;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Mood;
import com.rhymthwave.entity.SongStyle;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/profile")
@RequiredArgsConstructor
public class API_Profile {
		
	private final AccountService accountService;
	
	@GetMapping
	public ResponseEntity<?> getProfile(final HttpServletRequest request) {

		Account admin = accountService.findAdminByEmail(request);
		
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "Successfully", admin));
	}

}
