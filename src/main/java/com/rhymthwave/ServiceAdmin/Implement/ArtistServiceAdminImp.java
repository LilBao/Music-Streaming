package com.rhymthwave.ServiceAdmin.Implement;

import org.springframework.stereotype.Service;

import com.rhymthwave.Service.CRUD;
import com.rhymthwave.ServiceAdmin.IArtistService;
import com.rhymthwave.entity.Artist;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceAdminImp implements IArtistService{

	private final CRUD<Artist, Integer> crud;
	
	@Override
	public Artist getOneArtist(Integer id) {
		Artist artist = crud.findOne(id);
		if(artist == null) {
			return null;
		}
		
		return artist;
	}

}
