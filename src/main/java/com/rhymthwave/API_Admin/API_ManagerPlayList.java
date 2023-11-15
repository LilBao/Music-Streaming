package com.rhymthwave.API_Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhymthwave.DTO.MessageResponse;
import com.rhymthwave.ServiceAdmin.IPlayListServiceAdmin;
import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.Recording;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin/playlist")
public class API_ManagerPlayList {

	@Autowired
	private IPlayListServiceAdmin playListService;

	@GetMapping("/record-random")
	public ResponseEntity<?> getSongByStyle(@RequestParam(value = "nameGenre",required = false) String nameGenre,
			@RequestParam(value ="culture",required = false) String culture, @RequestParam(value ="mood",required = false) String mood,
			@RequestParam(value ="songstyle",required = false) String songstyle, @RequestParam(value ="instrument",required = false) String instrument) {
		List<Recording> list = playListService.getListRecordRandom(nameGenre, culture, mood, songstyle, instrument);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "success", list));
	}
	
	@GetMapping("/episode-random")
	public ResponseEntity<?> getSongByStyle(@RequestParam("tag") Integer tagID) {
		List<Episode> list = playListService.getListEpisodeRandom(tagID);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "success", list));
	}


	record imgPlaylist(MultipartFile files) {

	};

	@PostMapping()
	public ResponseEntity<?> createPlaylist(@RequestParam("file") MultipartFile file,
			@RequestParam("playlistName") String playlistName, @RequestParam("listRecord") String listRecordJson,
			@RequestParam("description") String description, HttpServletRequest request) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			List<Recording> listRecord = objectMapper.readValue(listRecordJson, new TypeReference<List<Recording>>() {
			});
			Playlist playlist = playListService.createPlayListForSongs(file, playlistName, description, listRecord, request);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, "success",playlist));
		} catch (JsonProcessingException e) {
			e.printStackTrace(); // Handle the exception appropriately
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageResponse(false, "Errorr handle objectMapper"));

		}

	}

}
