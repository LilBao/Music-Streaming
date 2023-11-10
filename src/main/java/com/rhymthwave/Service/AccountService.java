package com.rhymthwave.Service;

import com.rhymthwave.entity.Account;

import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {
	Account findAdminByEmail(HttpServletRequest request);
	
	Account findAccountByUsername(String username);
}
