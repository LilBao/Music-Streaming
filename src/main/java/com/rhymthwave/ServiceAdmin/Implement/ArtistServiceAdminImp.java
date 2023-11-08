package com.rhymthwave.ServiceAdmin.Implement;

import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.ArtistDAO;
import com.rhymthwave.DAO.AuthorDAO;
import com.rhymthwave.ServiceAdmin.IArtistService;
import com.rhymthwave.entity.Artist;
import com.rhymthwave.entity.Author;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceAdminImp implements IArtistService{

	
	private final ArtistDAO artistDAO;
	private final AuthorDAO authorDAO;
	@Override
	public Artist getOneArtistByEmail(String id) {
		Artist artist = artistDAO.findByEmail(id);
		if(artist == null) {
			return null;
		}
		
		return artist;
	}


	@Override
	public Object TotalAlbumAndSong(String idAccount) {
		
		return artistDAO.totalAlbumAndSong(idAccount);
	}


	@Override
	public int sumListenedArtist(String idAccount) {
		String sum = artistDAO.sumListenedArtist(idAccount);
		if(sum == null) {
			return 0;
		}
		return Integer.parseInt(sum);
	}


	@Override
	public int followerArtist(Integer idRole, String idAccount) {
		Author author = authorDAO.findAuthor(idRole, idAccount);
		
		return artistDAO.countFollowerArtist(author.getAuthorId());
	}

}
