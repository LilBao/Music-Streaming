package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.AccountDAO;
import com.rhymthwave.DAO.SongDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.SongService;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Song;

@Service
public class SongServiceImpl implements SongService, CRUD<Song, Integer> {
	@Autowired
	SongDAO dao;
	
	@Autowired
	AccountDAO accDao;

	@Override
	public Song create(Song entity) {
		if (entity != null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	public Song update(Song entity) {
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
	public Song findOne(Integer key) {
		if (key instanceof Integer && key >= 0) {
			return dao.findById(key).get();
		}
		return null;
	}

	@Override
	public List<Song> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Song> findSongNotRecord(String email) {
		Account account = accDao.findById(email).get();
		return dao.getSongNotRecord(account.getArtists().getArtistId());
	}
	

}
