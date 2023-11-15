package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.Recording;
import com.rhymthwave.entity.UserType;

public interface PlaylistService {

	List<Playlist> findMyPlaylist(UserType userType);
	
	Playlist createSimilarPodcast(Playlist playlist,List<Recording> list);
	
	List<Playlist> findPublicPlaylist(UserType userType, Boolean isPublic);
}
