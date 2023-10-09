package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.GenreDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Genre;

import jakarta.transaction.Transactional;

@Service
public class GenreServiceImpl implements CRUD<Genre, Integer>{
	@Autowired
	GenreDAO dao;

	@Override
	@Transactional
	public Genre create(Genre entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Genre update(Genre entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean delete(Integer key) {
		if(key instanceof Integer && key>0) {
			dao.deleteById(key);
			return true;
		}
		return false;
	}

	@Override
	public Genre findOne(Integer key) {
		if(key instanceof Integer && key>0) {
			return dao.findById(key).get();
		}
		return null;
	}

	@Override
	public List<Genre> findAll() {
		return dao.findAll();
	}
	
}
