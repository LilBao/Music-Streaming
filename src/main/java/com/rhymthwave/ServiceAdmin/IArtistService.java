package com.rhymthwave.ServiceAdmin;

import java.util.List;

import com.rhymthwave.entity.Artist;

public interface IArtistService {


	Artist getOneArtistByEmail(String email);

	Object TotalAlbumAndSong(String idAccount);
	
	int sumListenedArtist (String idAccount);

	int followerArtist(Integer idRole, String idAccount);	
	
	List<Artist> getIsVerityArtist();

	Artist approveRolesArtist(Integer idUser);
}
	