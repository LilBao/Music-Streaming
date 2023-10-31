package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.ArtistDAO;
import com.rhymthwave.Service.ArtistService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Artist;

import jakarta.transaction.Transactional;

@Service
public class ArtistServiceImpl implements ArtistService, CRUD<Artist, Integer> {
	@Autowired
	ArtistDAO dao;

	@Override
	@Transactional
	public Artist create(Artist entity) {
		entity.setVerify(false);
		return dao.save(entity);
	}

	@Override
	@Transactional
	public Artist update(Artist entity) {
		return dao.save(entity);
	}

	@Override
	@Transactional
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

	@Override
	public List<Artist> findIsVerify(Boolean verify) {
		return dao.findAllIsVerify(verify);
	}
	
	@Override
	public List<Artist> findAllArtistNameisVerify(Long id,String artistName) {
		return dao.findAllArtistVerify(id,artistName);
	}

}
