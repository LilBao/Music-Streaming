package com.rhymthwave.API_GraphQL_Admin;

import com.rhymthwave.ServiceAdmin.IPlayListServiceAdmin;
import com.rhymthwave.entity.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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

	@QueryMapping("getPlayListById")
	public Playlist  getPlayListById(@Argument("idPlaylist") Long id){return playList.findById(id);}



}
