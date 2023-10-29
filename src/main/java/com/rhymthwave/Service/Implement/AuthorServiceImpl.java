package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AuthorDAO;
import com.rhymthwave.Service.AuthorService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService, CRUD<Author, Long>{

	@Autowired
	AuthorDAO dao;
	
	@Override
	public Author create(Author entity) {
		return dao.save(entity);
	}



	@Override
	public Author update(Author entity) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean delete(Long key) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Author findOne(Long key) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Author> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Author findAuthor(Integer idRole, String email) {
		return dao.findAuthor(idRole, email);
	}
	
	
}
