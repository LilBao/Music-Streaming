package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudinary.provisioning.Account;
import com.rhymthwave.Service.AccountService;
import com.rhymthwave.Service.CRUD;

@Service
public class AccountServiceImpl implements AccountService, CRUD<Account, Integer> {

	@Override
	public Account create() {
		
		return null;
	}

	@Override
	public Account update() {
		
		return null;
	}

	@Override
	public Account delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findOne(Integer key) {
		
		return null;
	}

	@Override
	public List<Account> findAll() {
		
		return null;
	}
	
}
