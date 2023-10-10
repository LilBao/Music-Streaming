package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DAO.AlbumDAO;
import com.rhymthwave.Service.AlbumService;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Album;

@Service
public class AlbumServiceImpl implements AlbumService, CRUD<Album, Integer> {

	@Autowired
	AlbumDAO dao;
	
	@Autowired
	AccountDAO accDao;

	@Override
	public Album create(Album entity) {
		if (entity != null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	public Album update(Album entity) {
		if (entity != null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	public Boolean delete(Integer key) {
		if (key instanceof Integer && key >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public Album findOne(Integer key) {
		if (key instanceof Integer && key >= 0) {
			return dao.findById(key).get();
		}
		return null;
	}

	@Override
	public List<Album> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Album> findAlbumNotRecord(String email) {
		Account account = accDao.findById(email).get();
		return dao.getAlbumNotTrack(account.getArtists().getArtistId());
	}
	

}
