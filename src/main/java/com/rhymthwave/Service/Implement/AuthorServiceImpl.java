package com.rhymthwave.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AuthorDAO;
import com.rhymthwave.Service.AuthorService;
import com.rhymthwave.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	AuthorDAO dao;
	
	@Override
	public Author findAuthor(Integer roleId, String email) {
		return dao.findAuthor(roleId, email);
	}
	
	
}
