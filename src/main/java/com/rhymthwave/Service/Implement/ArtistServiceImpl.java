package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.ArtistDAO;
import com.rhymthwave.Service.ArtistService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Artist;

@Service
public class ArtistServiceImpl implements ArtistService, CRUD<Artist, Integer> {
	@Autowired
	ArtistDAO dao;

	@Override
	public Artist create(Artist entity) {
		entity.setVerify(false);
		return dao.save(entity);
	}

	@Override
	public Artist update(Artist entity) {
		return dao.save(entity);
	}

	@Override
	public Boolean delete(Integer key) {
		dao.deleteById(key);
		return true;
	}

	@Override
	public Artist findOne(Integer key) {
		return dao.findById(key).get();
	}

	@Override
	public List<Artist> findAll() {
		return dao.findAll();
	}

	@Override
	public Artist findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
