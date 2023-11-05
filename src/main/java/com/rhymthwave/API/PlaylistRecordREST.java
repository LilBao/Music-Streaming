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
import com.rhymthwave.entity.PlaylistRecord;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PlaylistRecordREST {
	
	private final CRUD<PlaylistRecord, Long> crudPlaylistRecord;
	
	@PostMapping("/api/v1/playlist-record")
	public ResponseEntity<MessageResponse> additionSongIntoPlaylist(@RequestBody PlaylistRecord playlistRecord){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudPlaylistRecord.create(playlistRecord)));
	}
	
	@DeleteMapping("/api/v1/playlist-record/{id}")
	public ResponseEntity<MessageResponse> additionSongIntoPlaylist(@PathVariable("id") Long id){
		return ResponseEntity.ok(new MessageResponse(true,"success",crudPlaylistRecord.delete(id)));
	}
}
