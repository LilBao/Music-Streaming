package com.rhymthwave.ServiceAdmin.Implement;

import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.ArtistDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.IArtistService;
import com.rhymthwave.entity.Artist;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceAdminImp implements IArtistService{

	
	private final ArtistDAO artistDAO;
	
	@Override
	public Artist getOneArtistByEmail(String id) {
		Artist artist = artistDAO.findByEmail(id);
		if(artist == null) {
			return null;
		}
		
		return artist;
	}

}
