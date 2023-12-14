package com.rhymthwave.Service;

import java.util.List;
import com.rhymthwave.entity.Account;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountService extends CRUD<Account, String>{

	Account findAdminByEmail(HttpServletRequest request);

	List<Object> search(String keyword);
	List<Object> searchArt(long id);
	List<Object> searchPl(long id);
	List<Object> searchAl(int id);
	List<Object> searchGr(String keyword);

	Account findAccountByUsername(String username);

}
