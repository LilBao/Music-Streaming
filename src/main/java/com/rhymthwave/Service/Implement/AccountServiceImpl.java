package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.Service.AccountService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Account;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService, CRUD<Account, String> {

	@Autowired
	AccountDAO dao;

	@Override
	public Account create(Account entity) {
		return null;
	}

	@Override
	public Account update(Account entity) {
		return null;
	}

	@Override
	public Boolean delete(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findOne(String key) {
		return dao.findById(key).get();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	@Transactional
//	public UserDetails loadUserByEmail(String email) {
//		Account account = dao.findById(email).get();
//		if(account == null) {
//			throw new UsernameNotFoundException(email);
//		}
//		return UserPrinciple.build(account);
//	}
	
}
