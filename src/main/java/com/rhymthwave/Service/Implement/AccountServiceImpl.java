package com.rhymthwave.Service.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.Service.AccountService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, CRUD<Account, String> {

	private final AccountDAO dao;

	private final GetHostByRequest getHostByRequest;

	@Override
	public Account create(Account entity) {
		return null;
	}

	@Override
	public Account update(Account entity) {

		return dao.save(entity);
	}

	@Override
	public Boolean delete(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findOne(String email) {
		Optional<Account> account = dao.findById(email);
		if (account.isEmpty()) {
			return null;
		}

		return account.get();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account findAdminByEmail(HttpServletRequest request) {
		String email = getHostByRequest.getEmailByRequest(request);
		Account admin = findOne(email);
		return admin;
	}

	@Override
	public List<Object> search(String keyword) {
		return dao.search(keyword);
	}

	public Account findAccountByUsername(String username) {
		Account account = dao.findByUsername(username);
		return account;
	}

}
