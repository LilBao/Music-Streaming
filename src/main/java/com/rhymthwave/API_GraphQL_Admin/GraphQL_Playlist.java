package com.rhymthwave.API_GraphQL_Admin;

import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rhymthwave.ServiceAdmin.IPlayListServiceAdmin;
import com.rhymthwave.entity.Playlist;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GraphQL_Playlist {

	
	private final IPlayListServiceAdmin playList;

	@QueryMapping("getAllPlaylist")
	public List<Playlist> getAllPlaylist() {
		return playList.getAllSongsPlaylist();
	}
	
	@QueryMapping("getAllPodcastPlaylist")
	public List<Playlist> getAllPodcastPlaylist() {
		return playList.getAllPodcastPlaylist();
	}
}
