package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Playlist;

public interface PlaylistService {

	List<Playlist> findMyPlaylist(String email);
}
