package com.rhymthwave.API;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Playlist_Podcast;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PlaylistPodcastREST {
	private final CRUD<Playlist_Podcast, Long> crudPlaylistPodcast;
	
	@PostMapping("/api/v1/playlist-episode")
	public ResponseEntity<MessageResponse> additionEpisodeIntoPlaylist(@RequestBody Playlist_Podcast playlistPodcast){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudPlaylistPodcast.create(playlistPodcast)));
	}
	
	@DeleteMapping("/api/playlist-episode/{id}")
	public ResponseEntity<MessageResponse> deleteEpisodeFromPlaylist(@PathVariable("id") Long id){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudPlaylistPodcast.delete(id)));
	}
}
