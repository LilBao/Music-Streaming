package com.rhymthwave.Service;


import com.rhymthwave.Request.DTO.AccountDTO;

import java.util.List;

import com.rhymthwave.entity.Account;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {

	Account findAdminByEmail(HttpServletRequest request);

	List<Object> search(String keyword);
	List<Object> searchArt(long id);
	List<Object> searchPl(long id);
	List<Object> searchAl(int id);
	List<Object> searchGr(String keyword);

	Account findAccountByUsername(String username);


	Account update(AccountDTO accountRequest, HttpServletRequest req, Account account);
	

}
