package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.Service.AccountService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Account;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, CRUD<Account, String> {

	private final AccountDAO dao;

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
		
		return dao.findById(email).get();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

//	@Override
//	public Page<Product> getByConditions(String namecate, Integer pageNo, Integer pageSize, String sortField,
//			String sortDirection, Integer size, Float minPrice, Float maxPrice) {
//		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
//		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//		return dao.getByConditions(namecate, minPrice, maxPrice, size, pageable);
//	}
}
