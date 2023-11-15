package com.rhymthwave.Service;

import com.rhymthwave.Request.DTO.AccountDTO;
import com.rhymthwave.entity.Account;

import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {
	Account findAdminByEmail(HttpServletRequest request);
	
	Account findAccountByUsername(String username);

	Account update(AccountDTO accountRequest, HttpServletRequest req, Account account);
	
}
